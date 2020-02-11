package huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 2019-01-01 08:59:00.123
 * 2019-01-01 08:59:00.123
 * 2018-12-28 10:08:00.999
 *
 *
 */

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        int count = 0;
        while (in.hasNext()) {
            String line = in.nextLine();
            int lastIndexOf = line.lastIndexOf(".");
            String key = line.substring(0, lastIndexOf);
            String value = line.substring(lastIndexOf + 1);
            if (map.get(key) != null) {
                ArrayList<Integer> integerArrayList = map.get(key);
                integerArrayList.add(Integer.parseInt(value));
                map.put(key, integerArrayList);
            } else {
                ArrayList<Integer> integerArrayList = new ArrayList<>();
                integerArrayList.add(Integer.parseInt(value));
                map.put(key, integerArrayList);
            }
        }
        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            ArrayList<Integer> integerArrayList = entry.getValue();
            Collections.sort(integerArrayList);
            int firstInteger = integerArrayList.get(0);
            for (Integer integer : integerArrayList) {
                if (integer == firstInteger) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

}
