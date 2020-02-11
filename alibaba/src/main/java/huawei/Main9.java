package huawei;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 *
 * 1.读取输入并以;分割
 * 2.合法性判断（以A+开头，后面是数字组成的字符串）
 * 3.分别以m记录横轴  n记录纵轴
 * 4.输出
 *
 * A10;S20;W10;D30;X;A1A;B10A11;;A10;
 *
 * 10,-10
 */

public class Main9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.length() > 0) {
                String[] strings = line.split(";");
                int m = 0, n=0;
                for (String s : strings) {
                    if (isLigeal(s) && isNumeric(s.substring(1))) {
                        if (s.startsWith("A")) {
                            m = m - Integer.parseInt(s.substring(1));
                        } else if (s.startsWith("D")) {
                            m = m + Integer.parseInt(s.substring(1));
                        } else if (s.startsWith("S")) {
                            n = n - Integer.parseInt(s.substring(1));
                        } else if (s.startsWith("W")) {
                            n = n + Integer.parseInt(s.substring(1));
                        }
                    }
                }
                System.out.println(m + "," + n);
            }
        }
    }

    public static boolean isLigeal(String str){
        return str.startsWith("A") || str.startsWith("D") || str.startsWith("S") || str.startsWith("W");
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
