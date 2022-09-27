package boj;

/*
    백트래킹을 이용해 선택할 수 있는 경우를 모두 선택한다.
    하나의 행의 모든 열을 탐색하면 다음 행으로 이동하며 탐색해간다.
    만약 1이 넴모를 채운것이고 0이 채우지 않을 것일때 다음과 같은 상황이 발생하면 -> 11
                                                                                 1(현재위치)
   다음과 같이 채워왔고 현재위치에 따라 2*2가 성립하는지 안하는지가 결정될때는 따로 예외를 두어 넴모를 채우지 않고 다음 열을 호출한다.
    그렇게 하면 모든 행과 열을 탐색하고 나면 2*2의 넴모가 채워지는 경우는 발생하지 않는다.
*/

import java.io.*;
import java.util.*;

class Boj14712 {
    static int n, m;
    static int[][] map;
    static int count;
    static int[] row = {-1, -1, 0};
    static int[] col = {-1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        backtracking(0, 0);

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void backtracking(int r, int c) {
        if (c == m) {
            r++;
            c = 0;
        }
        if (r == n) {
            count++;
            return;
        }

        // 현재 칸을 제외하고 나머지 3칸이 전부 1이라면 현재 칸은 1이되면 안되므로 바로 넘겨준다.
        if (!check(r, c)) {
            backtracking(r, c + 1);
            return;
        }

        map[r][c] = 1;
        backtracking(r, c + 1);
        map[r][c] = 0;
        backtracking(r, c + 1);
    }

    public static boolean check(int r, int c) {
        int oneCount = 0;
        for (int i = 0; i < 3; i++) {
            int dr = r + row[i];
            int dc = c + col[i];

            if (dr < 0 || dr >= n || dc < 0 || dc >= m) {
                return true;
            }
            if (map[dr][dc] == 1) {
                oneCount++;
            }
        }
        if (oneCount == 3) {
            return false;
        } else {
            return true;
        }
    }
}