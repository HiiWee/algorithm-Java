package boj;

/*
    운동기구가 홀수일떄와 짝수일때 차이가 존재
        1. 짝수: 짝수인 경우 제일 큰 수와 제일 작은 수가 짝이 되어야 최소를 얻을 수 있음
        2. 홀수: 홀수인 경우 제일 큰 수는 따로 두고, 나머지 짝수의 수에서 작은 수, 큰 수를 pair매칭 했을때
                min값보다 크다면 갱신하며 최소값을 구한다.
*/

import java.util.*;
import java.io.*;

class Boj20300 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long min = Long.MIN_VALUE;
        if (n % 2 == 0) {
            for (int i = 0; i < n / 2; i++) {
                min = Math.max(min, arr[i] + arr[n - 1 - i]);
            }
        } else {
            min = arr[n - 1];
            int size = (n - 1) / 2;
            for (int i = 0; i < size; i++) {
                min = Math.max(min, arr[i] + arr[n - 2 - i]);
            }
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }
}