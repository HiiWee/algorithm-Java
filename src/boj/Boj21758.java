package boj;

/*
    3가지의 케이스를 생각
    1. 왼쪽끝 벌, 오른쪽 끝 벌통, 나머지 벌 위치 잡으면서 최대값
    2. 오른쪽 끝 벌, 왼쪽 끝 벌통, 나머지 벌 위치 잡으면서 최대값
    3. 왼쪽, 오른쪽 모두 벌, 가운데 벌통 위치 잡으면서 최대값을 갱신해가면 됨
    이때 미리 누적합을 구해두고 벌의 위치만 빼주면 최대값을 구할 수 있다.
*/

import java.io.*;
import java.util.*;

class Boj21758 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        long[] sum = new long[n];
        long total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            total += arr[i];
            // 누적합 구하기
            sum[i] = total;
        }
        long max = Long.MIN_VALUE;
        // 왼쪽 끝 벌, 오른쪽 끝 벌통, 나머지 벌 위치 잡으며 최대값
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, sum[n - 1] - arr[0] - arr[i] + sum[n - 1] - sum[i]);
        }

        // 오른쪽 끝 벌, 왼쪽 끝 벌통, 나머지 벌 위치 잡으며 최대값
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, sum[n - 1] - arr[n - 1] - arr[i] + sum[i - 1]);
        }

        // 양쪽 끝 벌, 벌통 움직이며 최대값 갱신
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, sum[i] - arr[0] + sum[n - 1] - arr[n - 1] - sum[i - 1]);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }
}