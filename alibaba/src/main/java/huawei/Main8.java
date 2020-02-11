package huawei;

import java.util.Scanner;

/**
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a a
 */

public class Main8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(reverse(line));
        }
    }

    /**
     * 反转句子
     *
     * @param sentence 原句子
     * @return 反转后的句子
     */
    public static String reverse(String sentence) {
        if (sentence.length() == 0) {
            return "";
        }
        String[] strings = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            sb.append(strings[i]).append(" ");
        }
        return sb.toString();
    }
}
