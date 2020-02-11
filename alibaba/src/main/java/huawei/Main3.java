package huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 */

public class Main3 {
    private static final String SPLITTER_ON = " ";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int anInt = in.nextInt();
            HashMap<Integer, Integer> map = new HashMap<>(anInt);
            for (int i = 0; i <= anInt; i++) {
                String line = in.nextLine();
                if (line.length() > 0) {
                    String[] strings = line.split(SPLITTER_ON);
                    if (strings.length == 2) {
                        int key = Integer.parseInt(strings[0]);
                        int value = Integer.parseInt(strings[1]);
                        map.merge(key, value, Integer::sum);
                    }
                }
            }
            List<Integer> keyList = new ArrayList<>(map.size());
            keyList.addAll(map.keySet());
            Collections.sort(keyList);
            keyList.forEach(integer -> System.out.println(integer + " " + map.get(integer)));
        }
    }
}
