package com.alibaba.question3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 核心思想： 1. 生产者消费者模式 实现读文件和对文件内容排序 2. 栅栏 实现线程通信，确保在查询特定记录之前，文件中的所有记录都已经读入容器
 *
 * @author wangyuquan
 */
public class AnalysisFiles {

    /**
     * 存储文件中读取的记录，多个读线程共享
     */
    private SynchronousQueue<Record> recordSynchronousQueue = new SynchronousQueue<>();

    /**
     * 存储按照 group -> queue 键值对，queue是排序的优先队列
     */
    private ConcurrentHashMap<String, Queue<Record>> stringQueueConcurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 阻塞队列的操作超时时间
     */
    private static final int TIME_OUT_SECONDS = 3;

    /**
     * 文件中行记录的分隔符
     */
    private static final String SPLITTER = ",";

    /**
     * 目标文件夹路径
     */
    private static final String DIR_PATH = "/test";

    private ExecutorService executorService = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), new NamedThreadFactory("read_file_thread"));


    public static void main(String[] args) {
        AnalysisFiles analysisFiles = new AnalysisFiles();
        analysisFiles.mainProcess(DIR_PATH);
    }

    private void mainProcess(String dirPath) {
        try {
            // 参数检查
            assert dirPath != null;
            File[] filesList = getFilesList(dirPath);
            if (filesList == null || filesList.length == 0) {
                return;
            }

            CountDownLatch countDownLatch = new CountDownLatch(filesList.length);

            // 提交读文件任务
            submitReadFileTask(filesList, countDownLatch);

            // 提交排序任务
            submitSortAction(recordSynchronousQueue, stringQueueConcurrentHashMap);

            // 等待栅栏释放
            countDownLatch.await();

            // 打印结果
            printResult(stringQueueConcurrentHashMap);

        } catch (Exception e) {
            System.out.println("exception info:" + e);
        } finally {
            // 释放资源
            executorService.shutdown();
        }
    }

    private File[] getFilesList(String dirPath) {
        String root = AnalysisFiles.class.getResource("/").getPath();
        File dir = new File(root + dirPath);
        File[] tempList = null;
        if (dir.isDirectory()) {
            tempList = dir.listFiles();
        }
        return tempList;
    }

    private void submitReadFileTask(File[] tempList, CountDownLatch countDownLatch) {
        for (File value : tempList) {
            executorService.execute(new ProcessFileRunnable(value, countDownLatch));
        }
    }

    private void submitSortAction(SynchronousQueue<Record> recordSynchronousQueue,
                                  ConcurrentHashMap<String, Queue<Record>> stringQueueConcurrentHashMap) {
        executorService.execute(new SortRunnable(recordSynchronousQueue, stringQueueConcurrentHashMap));
    }

    private void printResult(ConcurrentHashMap<String, Queue<Record>> stringQueueConcurrentHashMap) {
        for (Map.Entry<String, Queue<Record>> entry : stringQueueConcurrentHashMap.entrySet()) {
            Record record = entry.getValue().peek();
            System.out.println(record);
        }
    }

    /**
     * 从队列中读取元素并排序
     */
    private class SortRunnable implements Runnable {

        private SynchronousQueue<Record> recordSynchronousQueue;
        private ConcurrentHashMap<String, Queue<Record>> stringQueueConcurrentHashMap;

        public SortRunnable(SynchronousQueue<Record> recordSynchronousQueue,
                            ConcurrentHashMap<String, Queue<Record>> stringQueueConcurrentHashMap) {
            this.recordSynchronousQueue = recordSynchronousQueue;
            this.stringQueueConcurrentHashMap = stringQueueConcurrentHashMap;
        }

        @Override
        public void run() {
            Record record;
            try {
                while ((record = recordSynchronousQueue.poll(TIME_OUT_SECONDS, TimeUnit.SECONDS)) != null) {
                    String groupId = record.getGroupId();
                    if (stringQueueConcurrentHashMap.get(groupId) != null) {
                        stringQueueConcurrentHashMap.get(groupId).add(record);
                    } else {
                        Queue<Record> recordQueue = new PriorityQueue<>((o1, o2) -> Float.compare(o1.quota, o2.quota));
                        recordQueue.add(record);
                        stringQueueConcurrentHashMap.put(groupId, recordQueue);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件内容到指定容器
     */
    private class ProcessFileRunnable implements Runnable {
        private File file;
        private CountDownLatch countDownLatch;

        public ProcessFileRunnable(File file, CountDownLatch countDownLatch) {
            this.file = file;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            // read file and put to queue
            if (file == null || !file.isFile()) {
                return;
            }

            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                String lineTxt;
                while ((lineTxt = br.readLine()) != null) {
                    String[] elements = lineTxt.split(SPLITTER);
                    if (elements.length == 3) {
                        Record record = new Record();
                        record.setId(elements[0]);
                        record.setGroupId(elements[1]);
                        record.setQuota(Float.parseFloat(elements[2]));
                        recordSynchronousQueue.offer(record, TIME_OUT_SECONDS, TimeUnit.SECONDS);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }
        }
    }

    private class Record implements Comparable<Record> {
        private String id;
        private String groupId;
        private float quota;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public float getQuota() {
            return quota;
        }

        public void setQuota(float quota) {
            this.quota = quota;
        }

        @Override
        public int compareTo(Record o) {
            return Float.compare(o.quota, this.quota);
        }

        @Override
        public String toString() {
            return groupId + "," + id + "," + quota;
        }
    }

    static class NamedThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory(String name) {

            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            if (null == name || name.isEmpty()) {
                name = "pool";
            }

            namePrefix = name + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}