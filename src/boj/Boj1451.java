package boj;

/**
 * 먼저 나올 수 있는 직사각형의 모양을 모두 찾아야함
 * 직사각형의 나누는 종류는 6개가 존재
 * ㅏ ㅓ ㅗ ㅜ = ||
 *
 * 각각의 직사각형의 모양에서의 합의 곱을 모두 구하면서 가장 큰 값을 갱신한다.
 */

import java.io.*;
import java.util.*;

class Boj1451 {
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                int num = line.charAt(j) - '0';
                arr[i][j] = num;
            }
        }

        long maxTotal = Integer.MIN_VALUE;
        long r1, r2, r3;
        long sum;
        // = 계산
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                r1 = getSum(0, 0, i, M - 1);
                r2 = getSum(i + 1, 0, j, M - 1);
                r3 = getSum(j + 1, 0, N - 1, M - 1);
                sum = r1 * r2 * r3;
                maxTotal = Math.max(maxTotal, sum);
            }
        }

        // || 계산
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                r1 = getSum(0, 0, N - 1, i);
                r2 = getSum(0, i + 1, N - 1, j);
                r3 = getSum(0, j + 1, N - 1, M - 1);
                sum = r1 * r2 * r3;
                maxTotal = Math.max(maxTotal, sum);
            }
        }

        // = 계산

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                // ㅏ 계산
                r1 = getSum(0, 0, N - 1, j);
                r2 = getSum(0, j + 1, i, M - 1);
                r3 = getSum(i + 1, j + 1, N - 1, M - 1);
                sum = r1 * r2 * r3;
                maxTotal = Math.max(maxTotal, sum);

                // ㅓ 계산
                r1 = getSum(0, 0, i, j);
                r2 = getSum(i + 1, 0, N - 1, j);
                r3 = getSum(0, j + 1, N - 1, M - 1);
                sum = r1 * r2 * r3;
                maxTotal = Math.max(maxTotal, sum);

                // ㅗ 계산
                r1 = getSum(0, 0, i, j);
                r2 = getSum(0, j + 1, i, M - 1);
                r3 = getSum(i + 1, 0, N - 1, M - 1);
                sum = r1 * r2 * r3;
                maxTotal = Math.max(maxTotal, sum);

                // ㅜ 계산
                r1 = getSum(0, 0, i, M - 1);
                r2 = getSum(i + 1, 0, N - 1, j);
                r3 = getSum(i + 1, j + 1, N - 1, M - 1);
                sum = r1 * r2 * r3;
                maxTotal = Math.max(maxTotal, sum);
            }
        }
        bw.write(maxTotal + "\n");
        bw.flush();
        bw.close();
    }

    public static long getSum(int startR, int startC, int endR, int endC) {
        long sum = 0;
        for (int i = startR; i <= endR; i++) {
            for (int j = startC; j <= endC; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }
}