package boj;

import java.io.*;
import java.util.*;

class Boj10421 {

    static int n, k;
    static int[] counts;
    static int[] numbers;
    static int count;
    static boolean[] check = new boolean[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        counts = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            counts[i] = Integer.parseInt(st.nextToken());
        }

        k = Integer.parseInt(br.readLine());
        numbers = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            check[numbers[i]] = true;
        }

        search(0, new int[counts[0] + counts[1]]);

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] out) {
        if (index == out.length) {
            if (isPossible(out)) {
                count++;
            }
            return;
        }

        for (int number : numbers) {
            out[index] = number;
            if (isPossibleNumber(index + 1, out)) {
                search(index + 1, out);
            }
        }
    }

    public static boolean isPossibleNumber(int count, int[] out) {
        if (count > counts[0]) {
            int first = 0;
            for (int i = 0; i < counts[0]; i++) {
                first = first * 10 + out[i];
            }
            String value = String.valueOf(out[count - 1] * first);
            if (value.length() != counts[2 + counts[1] - (count - counts[0])]) {
                return false;
            }
            for (int i = 0; i < value.length(); i++) {
                if (!check[value.charAt(i) - '0']) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPossible(int[] out) {
        int first = 0;
        int second = 0;
        for (int i = 0; i < counts[0]; i++) {
            first = first * 10 + out[i];
        }
        for (int i = counts[0]; i < out.length; i++) {
            second = second * 10 + out[i];
        }
        String result = String.valueOf(first * second);
        if (result.length() != counts[n - 1]) {
            return false;
        }
        for (int i = 0; i < result.length(); i++) {
            if (!check[result.charAt(i) - '0']) {
                return false;
            }
        }
        return true;
    }
}
