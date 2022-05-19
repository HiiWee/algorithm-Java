package boj;

import java.io.*;
import java.util.StringTokenizer;

// 에라토스테네스의 채 이용한 특정 구간의 모든 소수 구하기
class Boj1929 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];

        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (arr[i] == 1) {
                continue;
            }
            for (int j = i * 2; j <= n; j += i) {
                arr[j] = 1;
            }
        }
        for (int i = m; i <= n; i++) {
            if (arr[i] == 0) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}