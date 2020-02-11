package huawei;

import java.util.Scanner;

/**
 * 多组
 */

public class Main2 {
    private static final String SPLITTER_ON = " ";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String line = in.nextLine();
            System.out.println(Integer.decode(line));
        }
    }
}
