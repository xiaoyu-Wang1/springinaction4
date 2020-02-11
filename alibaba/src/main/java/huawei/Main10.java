package huawei;

import java.util.Scanner;

/**
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 */

public class Main10 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int anInt = in.nextInt();
            int count = 0;
            char[] chars = Integer.toBinaryString(anInt).toCharArray();
            for (Character character :chars) {
                if (Integer.parseInt(character.toString()) == 1) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
