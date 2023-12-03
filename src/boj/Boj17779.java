package boj;

import java.io.*;
import java.util.*;

class Boj17779 {

    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        int totalSum = 0;
        int minMinus = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalSum += map[i][j];
            }
        }

        // 경계를 나누기
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                for (int d1 = 1; d1 <= n - 1; d1++) {
                    for (int d2 = 1; d2 <= n - 1; d2++) {
                        if (!check(x, y, d1, d2)) {
                            continue;
                        }
                        int max;
                        int min;
                        boolean[][] visited = new boolean[n + 1][n + 1];
                        // 5구역 줄 긋기
                        drawLine(visited, x, y, d1, d2);

                        // 1구역 합 구하기
                        int firstSum = getFirstSection(visited, x, y, d1, d2);
                        // 2구역 합 구하기
                        int secondSum = getSecondSection(visited, x, y, d1, d2);
                        // 3구역 합 구하기
                        int thirdSum = getThirdSection(visited, x, y ,d1, d2);
                        // 4구역 합 구하기
                        int fourthSum = getFourthSection(visited, x, y, d1, d2);
                        // 5구역 구하기
                        int fifthSum = totalSum - (firstSum + secondSum + thirdSum + fourthSum);

                        // 가장 큰 값과 작은 값 차 구하고 최소값 갱신
                        max = Math.max(firstSum, Math.max(secondSum, Math.max(thirdSum, Math.max(fourthSum, fifthSum))));
                        min = Math.min(firstSum, Math.min(secondSum, Math.min(thirdSum, Math.min(fourthSum, fifthSum))));
                        minMinus = Math.min(minMinus, max - min);
                    }
                }
            }
        }
        bw.write(minMinus + "");
        bw.flush();
        bw.close();
    }

    public static int getFourthSection(boolean[][] visited, int x, int y, int d1, int d2) {
        int sum = 0;
        for (int r = x + d2 + 1; r <= n; r++) {
            for (int c = n; c >= y - d1 + d2; c--) {
                if (visited[r][c]) {
                    break;
                }
                sum += map[r][c];
            }
        }
        return sum;
    }

    public static int getThirdSection(boolean[][] visited, int x, int y, int d1, int d2) {
        int sum = 0;
        for (int r = x + d1; r <= n; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (visited[r][c]) {
                    break;
                }
                sum += map[r][c];
            }
        }
        return sum;
    }

    public static int getSecondSection(boolean[][] visited, int x, int y, int d1, int d2) {
        int sum = 0;
        for (int r = 1; r <= x + d2; r++) {
            for (int c = n; c > y; c--) {
                if (visited[r][c]) {
                    break;
                }
                sum += map[r][c];
            }
        }
        return sum;
    }

    public static int getFirstSection(boolean[][] visited, int x, int y, int d1, int d2) {
        int sum = 0;
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (visited[r][c]) {
                    break;
                }
                sum += map[r][c];
            }
        }
        return sum;
    }

    public static void drawLine(boolean[][] visited, int x, int y, int d1, int d2) {
        // 우상 대각
        for (int i = 0; i <= d1; i++) {
            visited[x + i][y - i] = true;
        }
        // 우하 대각
        for (int i = 0; i <= d2; i++) {
            visited[x + i][y + i] = true;
        }
        // 우상 -> 우하 대각
        for (int i = 0; i <= d2; i++) {
            visited[x + d1 + i][y - d1 + i] = true;
        }
        // 우하 -> 우상 대각
        for (int i = 0; i <= d1; i++) {
            visited[x + d2 + i][y + d2 - i] = true;
        }
    }

    public static boolean check(int x, int y, int d1, int d2) {
        return (x + d1 + d2 <= n)
                && (1 <= y - d1)
                && (y - d1 < y)
                && (y < y + d2)
                && (y + d2 <= n);
    }
}