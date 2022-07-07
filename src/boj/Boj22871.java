package boj;

import java.util.*;
import java.io.*;

// DP 풀이
class Boj22871_1 {
    static int[] A;
    static Long[] dp;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new Long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        long min = jump(0);
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }


    public static long calcK(int i, int j) {
        return (long) (j - i) * (long) (1 + Math.abs(A[i] - A[j]));
    }

    public static long jump(int start) {
        if (start == N - 1) {
            return 0;
        }
        if (dp[start] != null) {
            return dp[start];
        }
        dp[start] = Long.MAX_VALUE;
        for (int i = start + 1; i < N; i++) {
            dp[start] = Math.min(dp[start], Math.max(jump(i), calcK(start, i)));
        }
        return dp[start];
    }
}

// DP 풀이 2: Bottom-Up
class Boj22871_2 {
    static int N;
    static int[] A;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp[i] = Long.MAX_VALUE;
        }
        dp[0] = 0L;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.min(dp[i], Math.max(dp[j], calcK(j, i)));
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.println(dp[i]);
        }
        bw.write(dp[N - 1] + "\n");
        bw.flush();
        bw.close();

    }

    public static long calcK(int start, int end) {
        return (end - start) * (long) (1 + Math.abs(A[start] - A[end]));
    }
}

// 이분탐색 + 방문 체크를 이용한 풀이
class Boj22871_3 {
    static int N;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        long k = binarySearch();
        bw.write(k + "\n");
        bw.flush();
        bw.close();
    }

    public static long binarySearch() {
        long low = 0;
        long high = Long.MAX_VALUE;
        long mid;

        while (low < high) {
            mid = (low + high) / 2;

            boolean[] dp = new boolean[N];
            dp[0] = true;
            for (int i = 0; i < N; i++) {
                if (dp[i]) {
                    for (int j = i + 1; j < N; j++) {
                        // mid가 현재 기준 최소 k 이므로 k보다 작거나 같으면 지날 수 있다.
                        if (calcK(i, j) <= mid) {
                            dp[j] = true;
                        }
                    }
                }
            }
            if (dp[N - 1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static long calcK(int i, int j) {
        return (j - i) * (long) (1 + Math.abs(A[i] - A[j]));
    }
}

