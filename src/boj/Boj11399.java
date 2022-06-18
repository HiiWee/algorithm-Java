package boj;

import java.util.*;
import java.io.*;

class Boj11399 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] sumArr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        sumArr[0] = arr[0];
        int total = sumArr[0];
        // 0, 0 + 1, 0 + 1 + 2 ... 으로 누적합 구하기 DP를 이용해 누적값을 차례대로 구함
        // 이후 total변수에 누적합들의 합을 다시 구함
        for (int i = 1; i < N; i++) {
            sumArr[i] = arr[i] + sumArr[i - 1];
            total += sumArr[i];

        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}