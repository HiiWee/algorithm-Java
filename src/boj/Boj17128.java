package boj;

import java.io.*;
import java.util.*;

class Boj17128 {

    static int n, q;
    static int[] arr;
    static int total;
    static int[] sSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        arr = new int[n];
        sSum = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            int sum = 1;
            for (int j = i; j < i + 4; j++) {
                sum *= arr[j % n];
            }
            total += sum;
            sSum[i] = sum;
        }
        StringBuilder answer = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        while (q-- > 0) {
            int index = Integer.parseInt(st.nextToken()) - 1;
            arr[index] = -arr[index];

            int startIndex = (index - 3 + n) % n;
            for (int i = startIndex; i < startIndex + 4; i++) {
                int originSum = sSum[i % n];
                total -= 2 * originSum;
                sSum[i % n] = -originSum;
            }
            answer.append(total).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}