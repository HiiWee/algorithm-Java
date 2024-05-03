package boj;

import java.io.*;

class Boj10597 {

    static String number;
    static int max;
    static boolean[] check;
    static int[] outs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        number = br.readLine();
        if (number.length() <= 9) {
            max = number.length();
        } else {
            max = (number.length() - 9) / 2 + 9;
        }
        check = new boolean[max + 1];
        outs = new int[max];
        check[0] = true;
        search(0, 0);
    }

    public static void search(int outIdx, int numberIdx) {
        if (outIdx == max) {
            StringBuilder answer = new StringBuilder();
            for (int out : outs) {
                answer.append(out).append(" ");
            }
            System.out.println(answer);
            System.exit(0);
            return;
        }

        int length = Math.min(number.length(), numberIdx + 2);
        for (int i = numberIdx; i < length; i++) {
            int currentNumber = Integer.parseInt(number.substring(numberIdx, i + 1));
            if (currentNumber > max) {
                break;
            }
            if (!check[currentNumber]) {
                check[currentNumber] = true;
                outs[outIdx] = currentNumber;
                search(outIdx + 1, i + 1);
                check[currentNumber] = false;
            }
        }
    }
}
