package boj;

import java.io.*;
import java.util.*;

class Boj15658 {

    static int n;
    static int[] numbers;
    static int[] op = new int[4];
    static int[] out;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        out = new int[n - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        search(0, numbers[0]);

        bw.write(max + "\n" + min);
        bw.flush();
        bw.close();
    }

    public static void search(int count, int result) {
        if (count == n - 1) {
            min = Math.min(result, min);
            max = Math.max(result, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                search(count + 1, calculate(result, numbers[count + 1], i));
                op[i]++;
            }
        }
    }

    public static int calculate(int left, int right, int op) {
        if (op == 0) {
            left += right;
        } else if (op == 1) {
            left -= right;
        } else if (op == 2) {
            left *= right;
        } else {
            left /= right;
        }

        return left;
    }
}
