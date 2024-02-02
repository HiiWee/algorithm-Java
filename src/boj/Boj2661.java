package boj;

import java.io.*;

class Boj2661 {

    static int n;
    static boolean isEnd = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        search("");
    }

    public static void search(String number) {
        if (isEnd) {
            return;
        }
        if (number.length() == n) {
            System.out.println(number);
            isEnd = true;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            String temp = number + i;
            if (isPossible(temp)) {
                search(temp);
            }
        }
    }

    public static boolean isPossible(String number) {
        for (int i = 1; i <= number.length() / 2; i++) {
            String right = number.substring(number.length() - i);
            String left = number.substring(number.length() - i * 2, number.length() - i);
            if (left.equals(right)) {
                return false;
            }
        }
        return true;
    }
}