package huawei;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *描述：
 *
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 *
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 */

public class Main5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            StringBuilder sb = new StringBuilder().append(line).reverse();
            System.out.println(sb);
        }
    }
}
