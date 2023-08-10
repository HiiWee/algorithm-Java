package boj;

import java.io.*;
import java.util.*;

class Boj20542 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String answer = br.readLine();
        String correct = br.readLine();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (isMessy(answer.charAt(i - 1), correct.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        bw.write(dp[n][m] + "");
        bw.flush();
        bw.close();
    }

    private static boolean isMessy(char answer, char correct) {
        return (answer == correct)
                ||(answer == 'v' && correct == 'w')
                || (answer == 'i' && (correct == 'j' || correct == 'l'));
    }
}