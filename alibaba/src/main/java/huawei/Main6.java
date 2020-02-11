package huawei;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
 *
 * 1.字符串转为字符数组
 * 2.获取字符串的ACSII码，判断范围，并计数
 */

public class Main6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            Set<Character> characterSet = new HashSet<>();
            char[] charArray = line.toCharArray();
            for (Character c : charArray) {
                if (c > 0 && c < 127) {
                    characterSet.add(c);
                }
            }
            System.out.println(characterSet.size());
        }
    }
}
