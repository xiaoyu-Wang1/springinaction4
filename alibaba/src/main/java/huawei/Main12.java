package huawei;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。
 *
 * 1.len > 8 2.大、小写字母、数字、其他符号 中至少三种 3.不能有长度超2的字串重复出现
 *
 * 021Abc9000 021Abc9Abc1 021ABC9000 021$bc9000
 *
 * OK NG NG OK
 */

public class Main12 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            if (isLenMoreThan8(line) && isNotContainMoreThanTowRepeat(line) && isContainMoreThanThree(line)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }

    private static boolean isLenMoreThan8(String string) {
        return string != null && string.length() > 8;
    }

    private static boolean isNotContainMoreThanTowRepeat(String string) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length - 3; i++) {
            for (int j = i + 2; j < charArray.length - 3; j++) {
                if (string.substring(i, i + 3).equals(string.substring(j, j + 3))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isContainMoreThanThree(String string) {
        if (string == null) {
            return false;
        }

        int count = 0;
        String[] str = {"[a-z]", "[A-Z]", "[0-9]", "[^\\w]"};
        for (String s : str) {
            Pattern p = Pattern.compile(s);
            Matcher m = p.matcher(string);
            if (m.find()) {
                count++;
            }
        }

        return count >= 3;
    }
}
