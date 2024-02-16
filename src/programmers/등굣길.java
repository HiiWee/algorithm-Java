package programmers;

class 등굣길 {

    static final int DIV = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];

        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }

        dp[1][1] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == j && i == 1) {
                    continue;
                }
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % DIV;
            }
        }
        return dp[n][m];
    }
}