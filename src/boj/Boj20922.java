package boj;

import java.io.*;
import java.util.*;

class Boj20922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int[] counts = new int[100_001];
        int left = 0;
        int right = 0;
        int maxLength = 0;

        while (right < n) {
            int currentNumber = numbers[right++];
            counts[currentNumber]++;

            if (counts[currentNumber] > k) {
                while (counts[currentNumber] > k) {
                    counts[numbers[left++]]--;
                }
            } else {
                maxLength = Math.max(maxLength, right - left);
            }
        }

        bw.write(maxLength + "");
        bw.flush();
        bw.close();
    }
}