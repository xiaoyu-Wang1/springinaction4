package huawei;

import java.util.Scanner;

/**
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 * 1.小写字母都变成对应的数字，数字和其他的符号都不做变换，1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8
 * wxyz--9, 0--0, 2.大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。记住，z往后移是a哦。
 */

public class Main13 {
    private static final String dict1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String dict2 = "bcdefghijklmnopqrstuvwxyza22233344455566677778889999";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            char[] chars = line.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (Character c : chars) {
                char[] chars1 = dict1.toCharArray();
                char[] chars2 = dict2.toCharArray();
                boolean flag = false;
                for (int i = 0; i < chars1.length; i++) {
                    if (c == chars1[i]) {
                        sb.append(chars2[i]);
                        flag = true;
                    }
                }

                if (!flag) {
                    sb.append(c);
                }
            }
            System.out.println(sb);
        }
    }
}
