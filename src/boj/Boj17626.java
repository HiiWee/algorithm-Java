package boj;

/*
    DP로 풀어보자
    1 = 1
    2 = dp[1^2] + dp[1^2] = 2
    3 = dp[1^2] + dp[2] = 3
    4 = 1
    5 = dp[2^2] + dp[1]
    6 = dp[2^2] + dp[2]
    7 = dp[2^2] + dp[3]
    8 = dp[2^2] + dp[2^2]

    위의 규칙을 살펴보면 특정 수 이하의 (최대 제곱수의 경우의 수) + (최대 제곱수를 빼고 남은 경우의 수)로 나타낼 수 있다.
    즉 dp[a * a] + dp[b - a * a]가 된다. 여기서 a * a는 어차피 1개의 경우이므로  생략이 가능하다.
    따라서 점화식은 dp[b - a * a]이다.

    단 여기서 a * a가 b보다 작거나 같아야 하므로 2중 for문을 통해 쉽게 구할 수 있다.
*/

import java.io.*;

class Boj17626_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(dp[i - j * j], min);
            }
            dp[i] = min + 1;
        }

        bw.write(dp[n] + "\n");
        bw.flush();
        bw.close();
    }
}

/*
    특정 자연수를 제곱수의 합으로 나타내는데 최소 개수의 제곱수로 이루어지기 위해서는
    먼저 1개로 되는 경우를 확인하고 2개를 확인 이후 3개를 확인하는데
    각 3중 for문을 통해 확인한다.

    i, j, k가 각 제곱수이고, 초기 제곱수의 count는 4로두어 i, j, k를 전부 돌아도 찾지못하면 4를 출력하도록한다.

    (순수 완전탐색)
    i * i가 n일 경우 제곱수의 개수는 1이되고 아니라면 하위 반복문을 돌며 탐색
    i * i + j * j가 n일 경우 제곱수의 개수는 2이고 아니라면 하위 반복문을 돌며 탐색
    i* i + j * j + k * k가 n일 경우 제곱수의 개수는 3이고 아니라면 4개가 된다.

*/

class Boj17626_2 {
    static final int MAX = (int) Math.sqrt(50000) + 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int sqrtN = (int)Math.sqrt(n) + 1;

        int count = 4;
        for (int i = 1; i <= sqrtN; i++) {
            if (i * i == n) {
                count = 1;
                break;
            }
            for (int j = 1; j <= sqrtN; j++) {
                if (i * i + j * j > n) {
                    break;
                }
                if (i * i + j * j == n) {
                    count = 2;
                }
                if (count == 2) {
                    break;
                }
                for (int k = 1; k <= sqrtN; k++) {
                    if (i * i + j * j + k * k > n) {
                        break;
                    }
                    if (i * i + j * j + k * k == n) {
                        count = 3;
                        break;
                    }
                }
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}


