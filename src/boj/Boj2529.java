package boj;

import java.io.*;
import java.util.*;

class Boj2529 {

    static int k;
    static String[] inequalities;
    static String min, max;
    static long minValue = Long.MAX_VALUE, maxValue = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        k = Integer.parseInt(br.readLine());
        inequalities = new String[k];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            inequalities[i] = st.nextToken();
        }

        search(0, new boolean[10], "");

        bw.write(max + "\n" + min);
        bw.flush();
        bw.close();
    }

    public static void search(int index, boolean[] visited, String value) {
        if (index == k + 1) {
            long currentValue = Long.parseLong(value);
            if (minValue > currentValue) {
                minValue = currentValue;
                min = value;
            }
            if (maxValue < currentValue) {
                maxValue = currentValue;
                max = value;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i] && isPossible(index, value, i)) {
                visited[i] = true;
                search(index + 1, visited, value + i);
                visited[i] = false;
            }
        }
    }

    public static boolean isPossible(int index, String value, int number) {
        if (index == 0) {
            return true;
        }
        int previousNumber = value.charAt(value.length() - 1) - '0';
        if (inequalities[index - 1].equals("<")) {
            return previousNumber < number;
        }
        return previousNumber > number;
    }
}
