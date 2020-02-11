package huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 * 1.循环读取记录
 * 2.获取文件名，并取后16个有效字符
 * 3.文件名+ 逗号 +行号作为key，出现次数作为value
 * 4.从hashmap中找到value前8大的键值对并输出
 *
 * E:\V1R2\product\fpgadrive.c   1325
 * fpgadrive.c 1325 1
 */

public class Main11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            HashMap<String, Integer> map = new HashMap<>();
            while (in.hasNext()) {
                String line = in.nextLine();
                String[] strings = line.split(" ");
                if (strings.length == 2) {
                    String fileNameStr = strings[0];
                    String lineNum = strings[1];
                    String fileName = getFileName(fileNameStr);
                    String key = fileName + "," + lineNum;
                    map.merge(key, 1, Integer::sum);
                    HashMap<String, Integer> top8Value = findTop8Value(map);
                    printMap(top8Value);
                }
            }
        }
    }

    private static HashMap<String, Integer> findTop8Value(HashMap<String, Integer> map) {
        HashMap<String, Integer> resMap = new HashMap<>(8);
        List<Integer> valueList = new ArrayList<>(map.size());
        valueList.addAll(map.values());
        Collections.sort(valueList);
        map.forEach((key, value) -> {
            if (valueList.contains(map.get(key))) {
                resMap.put(key, value);
            }
        });
        return resMap;
    }

    private static void printMap(HashMap<String, Integer> map) {
        map.forEach((key, value) -> System.out.println(key.split(",")[0] + " " + key.split(",")[1] + " " + value));
    }

    private static String getFileName(String str) {
        String[] strings = str.split("\\\\");
        String fileName = strings[strings.length - 1];
        return fileName.length() <= 16 ? fileName : fileName.substring(strings.length - 17, strings.length - 1);
    }
}
