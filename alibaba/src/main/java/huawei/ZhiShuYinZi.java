package huawei;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 输出一个整数的质数因子，从小到大排序
 */

public class ZhiShuYinZi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            float aFloat = in.nextFloat();
            if ((aFloat - (int) aFloat) * 10 >= 5) {
                System.out.println((int) Math.ceil(aFloat));
            } else {
                System.out.println((int) Math.floor(aFloat));
            }
        }
    }
}
