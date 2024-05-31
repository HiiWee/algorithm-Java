package boj;

import java.io.*;
import java.util.*;

class Boj16938 {

    static int n, l, r, x;
    static int[] numbers;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        search(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int count, int min, int max, int sum) {
        if (index == n) {
            if (count >= 2 && sum >= l && sum <= r && max - min >= x) {
                answer++;
            }
            return;
        }

        int nextMin = Math.min(numbers[index], min);
        int nextMax = Math.max(numbers[index], max);
        int nextSum = sum + numbers[index];
        search(index + 1, count + 1, nextMin, nextMax, nextSum);
        search(index + 1, count, min, max, sum);
    }
}
