package boj;

/*

 * case 2에서, dp[n] > dp[n / 2] + 1 의 조건식에서,
 * dp[n]에 들어갈만한 정답이 dp[n-1]+1인지 dp[n/2]+1 인지를 비교해서 더 작은 값을 넣어야 하는데,
 * 이때 dp[n] 에 dp[n-1]+1이라는 값이 먼저 들어가 있어야 비교가 가능하기 때문에 case 3을 더 먼저 적어야 한다.
 *
 * */

import java.io.*;

class Boj1463 {
    static void calc(int n, int[] dp) {
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int n = Integer.parseInt(s);
        int[] dp = new int[n + 1];
        calc(n, dp);
        System.out.println(dp[n]);
    }
}




/*
    1: 0
    2: 2 / 2 = 1,                     1
    3: 3 / 3 = 1,                     1
    4: (4 - 1) / 3 = 1,               2
    5: 3안나눠짐, 2안나눠짐, dp[5 - 1] + 1   2 + 1, 3
    6: dp[6 / 3] + 1                    2
    7: dp[7 - 1] + 1                 3
*/


// 복습
class Boj1463_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];

        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i % 6 == 0) {
                dp[i] = Math.min(dp[i / 3], dp[i / 2]);
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3]);
            }  else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2]);
            }
            dp[i]++;
        }

        bw.write(dp[n] + "\n");
        bw.flush();
        bw.close();
    }
}
