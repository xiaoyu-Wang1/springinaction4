package huawei;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 *
 * 1 翻转整数
 * 2 数字去重：每读取一个则计入set集合
 * 3 输出
 */

public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int anInt = in.nextInt();
            String revertStr = new StringBuilder().append(anInt).reverse().toString();
            Set<Character> characterSet = new HashSet<>(revertStr.length());
            char[] charArray = revertStr.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (Character c : charArray) {
                if (!characterSet.contains(c)) {
                    sb.append(c);
                    characterSet.add(c);
                }
            }
            long aLong = Long.parseLong(sb.toString());
            System.out.println(aLong > Integer.MAX_VALUE ? 0 : aLong);
        }
    }
}
