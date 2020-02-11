package huaweifinal;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 密码生成器工具类
 *
 * @author wangyuquan
 * @date 2019-11-30 21:27
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            String line = scanner.nextLine();
            arrayList.add(line);
        }
        arrayList.forEach(s -> System.out.println(passwordGenerator(s)));
    }

    private static String passwordGenerator(String line) {
        // 是否合法
        if (line.length() < 6) {
            return "输入参数非法!";
        }

        // 1.折叠
        ArrayList<String> arrayList = new ArrayList<>();
        while (line.length() > 6) {
            arrayList.add(line.substring(0, 6));
            line = line.substring(6);
        }
        if (line.length() > 0) {
            arrayList.add(line);
        }

        // 2.求 ASCII 码
        ArrayList<String> targetList = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            StringBuilder sb = new StringBuilder();
            for (String s : arrayList) {
                if (s.length() > i) {
                    sb.append(s.charAt(i));
                }
            }
            targetList.add(sb.toString());
        }

        ArrayList<Integer> integers = new ArrayList<>(6);
        targetList.forEach(s -> integers.add(stringToAscii(s)));

        // 3.缩位运算
        StringBuilder sb = new StringBuilder();
        integers.forEach(integer -> sb.append(intToSmallInt(integer)));

        return sb.toString();
    }

    private static int stringToAscii(String value) {
        int sum = 0;
        char[] chars = value.toCharArray();
        for (char aChar : chars) {
            sum += (int) aChar;
        }
        return sum;
    }

    private static int intToSmallInt(int num) {
        int num1, num2;
        while (num > 9) {
            num1 = num % 10;
            num2 = num / 10;
            num = num1 + num2;
        }
        return num;
    }
}
