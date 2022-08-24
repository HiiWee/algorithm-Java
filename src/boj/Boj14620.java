package boj;

/*
    백트래킹을 통해 순차적으로 3개의 값을 계산해보고, 이후 3개의 꽃을 찾았다면 값을 갱신한다.
    이후 직전 재귀로 돌아와 해당 재귀에서 탐색한 꽃을 취소하고, 그 다음 꽃이 올 경우를 다시 재귀로 탐색하며 꽃이 올 수 있는 자리의 조합들을 전부 비교 계산하여 최소값을 찾아낸다.
    즉 1 2 3 4 5 6 ~~의 방법이 존재한다면 1 2 3 -> 1, 2, 4 -> 1, 2, 5 -> 1, 2, N -> 1, 3, ... 이런식으로 가능한 모든 경우를 찾는다.

    이렇게 여러개의 가지의 시작점에서 특정 가지로 뻗어나가다가 일정 조건을 만족하거나, 특정 조건을 불일치하게 되면 사용자가 임의로 해당 가지를 쳐낸다.
    이후 방문하지 않은 가지를 방문하는 기법을 백트래킹 이라고 한다.
*/
import java.io.*;
import java.util.*;

class Boj14620 {
    static int n;
    static int[][] map;
    static boolean[][] buy;
    static int totalPrice = Integer.MAX_VALUE;
    static int[] row = {0, -1, 0, 1, 0};
    static int[] col = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        buy = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        backTracking(0, 0);

        bw.write(totalPrice + "\n");
        bw.flush();
        bw.close();
    }


    public static void backTracking(int depth, int sum) {
        if (depth == 3) {
            totalPrice = Math.min(sum, totalPrice);
            return;
        }

        for (int i = 2; i <= n - 1; i++) {
            for (int j = 2; j <= n - 1; j++) {
                if (isPossibleToBuy(i, j)) {
                    int price = buyLand(i, j);
                    backTracking(depth + 1, sum + price);
                    refund(i, j);
                }
            }
        }
    }


    public static boolean isPossibleToBuy(int r, int c) {
        for (int i = 0; i < 5; i++) {
            int dr = r + row[i];
            int dc = c + col[i];
            if (buy[dr][dc]) {
                return false;
            }
        }
        return true;
    }

    public static int buyLand(int r, int c) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            int dr = r + row[i];
            int dc = c + col[i];
            sum += map[dr][dc];
            buy[dr][dc] = true;
        }
        return sum;
    }

    public static void refund(int r, int c) {
        for (int i = 0; i < 5; i++) {
            int dr = r + row[i];
            int dc = c + col[i];
            buy[dr][dc] = false;
        }
    }
}


