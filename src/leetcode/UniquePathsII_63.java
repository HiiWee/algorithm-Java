package leetcode;

/*
    우측 또는 아래로만 이동 가능
    각 자릿수를 돌면서 올 수 있는 경우를 저장한다.
    먼저 상단 외곽과, 좌측 외곽이 올 수 있는 경우는 우측, 하단밖에없으므로 1로 초기화
    이후 각 나머지 자릿수를 돌면서 더해준다.

    만약 장애물을 만나면 제외
*/
class UniquePathsII_63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        // 1열의 모든 행들은 1가지방법으로만 갈 수 있음,
        // 단 장애물을 만나면 그 아래로는 가지 못함
        for (int i = 0; i < dp.length; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        // 1행의 모든 열들은 1가지 방법으로만 갈 수 있음.
        // 단 장애물을 만나면 장애물 오른쪽으로 갈 수 없음
        for (int i = 0; i < dp[0].length; i++) {
            if (obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        // 현재 점 기준 좌측행, 상단행의 경우의 수를 더해가며 dp배열을 채움
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }
        // 시작지점이 1이라면 갈 수 있는 방법이 없다.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        } else {
            return dp[dp.length - 1][dp[0].length - 1];
        }
    }

    public static void main(String[] args) {
        int[][] testcase = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        UniquePathsII_63 solution = new UniquePathsII_63();

        int result = 2;
        int answer = solution.uniquePathsWithObstacles(testcase);

        if (result == answer) {
            System.out.println("Test is passed");
        }
    }
}