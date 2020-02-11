package huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 给定n个字符串，请对n个字符串按照字典序排列。
 */

public class Main7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int anInt = in.nextInt();
            List<String> stringList = new ArrayList<>(anInt);
            for (int i = 0; i < anInt; i++) {
                stringList.add(in.next());
            }
            Collections.sort(stringList);
            stringList.forEach(System.out::println);
        }
    }
}
