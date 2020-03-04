package LeetCode;

import java.time.temporal.Temporal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author chenshuai
 * @date 2019/9/4 23:07
 * BianHaoZhuanHuan.class
 */
public class BianHaoZhuanHuan {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int i = 0, n = scanner.nextInt();
        scanner.nextLine();

        String[] strings = new String[n];
        while (n > 0) {
            strings[i] = scanner.nextLine();

            i++;
            n--;
        }
        for (String s : strings) {
            if (s.charAt(0) == 'R' && s.charAt(1) >= '0' && s.charAt(1) <= '9') {

                System.out.println(RCToString(s));
            } else {

                System.out.println(StringToRC(s));
            }
        }

    }

    public static String RCToString(String rc) {
        StringBuilder stringBuilder = new StringBuilder();
        int row = Integer.parseInt(rc.substring(rc.indexOf('R') + 1, rc.indexOf('C')));
        int col = Integer.parseInt(rc.substring(rc.indexOf('C') + 1));
        int temp = col, b;
        do {
            b = temp % 26;


            stringBuilder.append((char) (b + 64));
            temp /= 26;

        } while (temp > 0);

        return stringBuilder.reverse().append(row).toString();
    }

    public static String StringToRC(String s) {

        int i;
        for (i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                break;
            }
        }
        int row = Integer.parseInt(s.substring(i + 1));
        int col = (int) s.charAt(i--) - 64;
        int count = 26;
        for (; i >= 0; ) {
            col += ((int) s.charAt(i--) - 64) * count;
            count *= 26;
        }
        return "R" + row + "C" + col;
    }
}
