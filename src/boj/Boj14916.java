package boj;

/*
1원: x
2원: 2원 1개
3원: x (2원으로 나누면 나머지 1)
4원: 2원 2게
5원: 5원 1개
6원: 2원 3개(5원 1개면 몫 1이고, 나머지 2이하이므로 안됨)
7원: 5원 1개 2원 1개
8원: 2원 4개 (5원 1개, 2원 1개 나머지 1이므로 안됨)
*/

import java.io.*;
import java.util.*;

// Greedy
class Boj14916_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        if (n == 1 || n == 3) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }


        int result;
        int count = n / 5;
        int left = n % 5;

        // 5로나눈 나머지가 1일떄
        if (left % 2 == 1) {
            // 만약 5로 나눈 몫이 1이라면, 전체를 2원으로 계산해야하는경우밖에 없음
            if (count == 1) {
                result = n / 2;
            } else {
                // 몫이 2 이상라면 홀수 + 홀수 = 짝수 정실을 이용해, 기존 5로나눈 몫을 1줄이고
                // 나머지에 5를 더한 후, 그 갑에서 2를 나눈 몫을 구하면 최소 동전이 된다.
                count--;
                left += 5;
                result = count + left / 2;
            }
        } else {
            // 나머지가 짝수라면 그대로 2로 나눈 몫 더함
            result = count + left / 2;
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}

// DP 풀이
class Boj14916_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 5];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[2] = 1;
        dp[4] = 2;
        dp[5] = 1;

        /*
        * i원을 거슬러주는 방법은 두 가지입니다.
        *
        * (i - 2)원을 거슬러줄 동전 조합에 2원짜리 동전을 하나 더 추가
        * (i - 5)원을 거슬러 줄 동전 조합에 5원짜리 동전을 하나 더 추가
        *
        * 따라서 둘 중의 최소값 + 1을 dp[i]에 저장한다.
        * (불가능한 값은 정수형 가장 큰 값이므로 선택되지 않는다.)
        * */
        for (int i = 6; i <= n; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 5]) + 1;
        }

        if (dp[n] == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(dp[n] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
