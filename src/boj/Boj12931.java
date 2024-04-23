package boj;

import java.io.*;
import java.util.*;

class Boj12931 {

    static int n;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];

        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            if (numbers[i] == 0) {
                zeroCount++;
            }
        }

        int count = 0;
        while (zeroCount < numbers.length) {
            if (isEven()) {
                for (int i = 0; i < n; i++) {
                    numbers[i] /= 2;
                }
                count++;
            }

            for (int i = 0; i < n; i++) {
                if (numbers[i] % 2 == 0) {
                    continue;
                }
                numbers[i]--;
                count++;
                if (numbers[i] == 0) {
                    zeroCount++;
                }
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static boolean isEven() {
        for (int i = 0; i < n; i++) {
            if (numbers[i] % 2 == 1) {
                return false;
            }
        }
        return true;
    }
}
