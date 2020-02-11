package huawei;

import java.util.Scanner;

/**
 * 近似值
 */

public class JinSiZhi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            int num = in.nextInt();
            if (num == 1) {
                System.out.println(num + " ");
            }

            for (int i = 2; i <= num; ++i) {
                if (num % i == 0) {
                    sb.append(i).append(" ");
                    num = num / i;
                    i--;
                }
            }
            System.out.println(sb);
        }
    }
}
