package huawei;

import java.util.Scanner;

/**
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 * 1.小写字母都变成对应的数字，数字和其他的符号都不做变换，
 * 2.大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。记住，z往后移是a哦。
 */

public class MainDemo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(line);
        }
    }

}
