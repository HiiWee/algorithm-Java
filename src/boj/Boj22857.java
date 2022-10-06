package boj;

/*
    투 포인터를 이용해 풀어보자
    l, r은 같은 점에서 시작해 r은 포인터를 증가시키며 홀수를 만나면 카운트 값을 증가시킨다,
    만약 카운트의 값과 k의 수가 동일해지면 k개의 홀수를 제거했을때 l과 r 사이에 있는 짝수의 값이 길이가 되므로
    최대값을 갱신한다.

    이후 만약 count가 k와 동일하다면 l을 증가시키며 l인덱스의 값이 홀수라면 카운트를 뺴준다. 이후 다시 r을 증가시키며
    전체 수열에서 부분수열의 최대값을 탐색한다.
*/

import java.io.*;
import java.util.*;

class Boj22857 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 0;
        int count = 0;
        int answer = 0;

        while (r < n) {
            if (count <= k) {
                count += arr[r++] % 2;
            } else {
                count -= arr[l++] % 2;
            }
            if (count <= k) {
                answer = Math.max(answer, r - l - count);
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}

/*
    2차원 dp를 선언하여 행은 0 ~ k개의 수를 제거했을때를 길이를 구분하고, 각 열들은 n개의 수에서의 최대 부분수열의 길이를 나타냄

    우선 각 수를 받아와서 해당수가 짝수라면 dp[0][i] = dp[0][i - 1] + 1을 해준다.
    이렇게 하는 이유는 직전에 방문한 수의 길이가 1이라면 현재길이까지 포함해 2의 길이를 가지기 때문이다.

    이렇게 0개의 수를 제거했다면 1 ~ k개의 수를 제거했을때도 판단해주어야 한다.
    따라서 또다시 1 ~ k의 반복을 하며 현재 수(i)가 홀수인지 짝수인지에 따라 dp를 구분해 채워준다.

    1. 짝수인 경우
        i가 짝수라면 현재 수를 빼지 않아도 되므로 빼는 수는 유지하고 현재 이전의 수에서 홀수를 제거해야 한다.
        이후 이전의 수에서 최대값을 저장한다.
        따라서 dp[j][i] = dp[j][i - 1] + 1의 수를 저장한다.
    2. 홀수인 경우
        i가 홀수라면 현재의 수를 제거해야 짝수 연속 부분수열이 성립할 수 있으므로 제거하고, 이전의 최대값을 저장한다.
        dp[j][i] = dp[j - 1][i - 1] --> 현재 수가 홀수이므로 1을 더하지 않음

    모든 dp를 채웠다면
    제거할 수 있는 홀수를 제거했을때의 연속 짝수 부분수열의 길이가 가장 길게 나오므로
    dp[k][i] (i == 1 ~ n) 에서의 최대값을 선택한다.
*/

// dp 풀이
class Boj22857_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[k + 1][n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num % 2 == 0) {
                dp[0][i] = dp[0][i - 1] + 1;
            }

            for (int j = 1; j <= k; j++) {
                if (num % 2 == 0) {
                    dp[j][i] = dp[j][i - 1] + 1;
                } else {
                    dp[j][i] = dp[j - 1][i - 1];
                }
            }
        }

        int max = -1;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dp[k][i]);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }
}