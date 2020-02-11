package chapter03;

class ReorderExample {
    int     a    = 0;
    boolean flag = false;

    public void writer() throws InterruptedException {
        a = 1; //1
        Thread.sleep(1000);
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a * a; //4
            //s¡­¡­
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        final ReorderExample example = new ReorderExample();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    example.writer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                example.reader();
            }
        }).start();
    }
}
