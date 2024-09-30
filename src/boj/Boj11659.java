package boj;

import java.io.*;
import java.util.*;

class Boj11659 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[n + 1];
        for (int i = 0; i < n; i++) {
            numbers[i + 1] = numbers[i] + Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            answer.append(numbers[end] - numbers[start - 1]).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
