package boj;

import java.util.*;
import java.io.*;

// 투 포인터 알고리즘: 모든 수가 자연수일 경우 이용할 수 있는 알고리즘 중 하나
// (범위를 늘리면 값이 증가, 줄이면 값이 감소하는것이 확실하게 보장되므로)
class Boj2003 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int count = 0;
        int start = 0;
        int end = 0;
        int total = 0;

        while (end <= N) {
            // 값이 작다면 범위를 늘림, end 증가
            if (total < M) {
                total += arr[end];
                end++;
            // 값이 크거나 같다면 범위를 좁힘, start 증가
            } else if (total >= M) {
                total -= arr[start];
                start++;
            }
            // 값이 같다면 카운팅
            if (total == M) {
                count++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}