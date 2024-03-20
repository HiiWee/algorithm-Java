package boj;

import java.io.*;

class Boj12904_1 {

    static String s;
    static boolean isFind;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        s = br.readLine();
        String t = br.readLine();

        search(t, false);

        if (isFind) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
    }

    public static void search(String t, boolean isReverse) {
        if (isFind || t.length() < s.length()) {
            return;
        }
        if (t.length() == s.length() && isSame(t, isReverse)) {
            isFind = true;
            return;
        }

        if (isReverse) {
            if (t.charAt(0) == 'A') {
                search(t.substring(1), true);
            }
            if (t.charAt(0) == 'B') {
                search(t.substring(1), false);
            }
        } else {
            if (t.charAt(t.length() - 1) == 'A') {
                search(t.substring(0, t.length() - 1), false);
            }
            if (t.charAt(t.length() - 1) == 'B') {
                search(t.substring(0, t.length() - 1), true);
            }
        }
    }

    public static boolean isSame(String t, boolean isReverse) {
        if (isReverse) {
            return new StringBuilder(t).reverse().toString().equals(s);
        } else {
            return t.equals(s);
        }
    }
}


class Boj12904_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder s = new StringBuilder(br.readLine());
        StringBuilder t = new StringBuilder(br.readLine());

        while (t.length() > s.length()) {
            if (t.charAt(t.length() - 1) == 'A') {
                t = t.deleteCharAt(t.length() - 1);
            } else {
                t = t.deleteCharAt(t.length() - 1);
                t.reverse();
            }
        }

        if (t.toString().contentEquals(s)) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
    }
}