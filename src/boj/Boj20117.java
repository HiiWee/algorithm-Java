package boj;

import java.io.*;
import java.util.*;

class Boj20117 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int bestBenefit = 0;
        if (n % 2 == 0) {
            for (int i = n - 1; i >= n / 2; i--) {
                bestBenefit += numbers[i] * 2;
            }

        } else {
            for (int i = n - 1; i > n / 2; i--) {
                bestBenefit += numbers[i] * 2;
            }
            bestBenefit += numbers[n / 2];
        }

        bw.write(Integer.toString(bestBenefit));
        bw.flush();
        bw.close();
    }
}

