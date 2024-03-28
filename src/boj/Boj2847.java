package boj;

import java.io.*;

class Boj2847 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        int decreasingCount = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (numbers[i] >= numbers[i + 1]) {
                int diff = Math.abs(numbers[i] - numbers[i + 1]) + 1;
                numbers[i] -= diff;
                decreasingCount += diff;
            }
        }

        bw.write(Integer.toString(decreasingCount));
        bw.flush();
        bw.close();
    }
}
