package boj;

/*
    특정 점에서 백트래킹을 통해 상하좌우 4방향으로 딱 4번만 탐색하면 하나의 테트로미노를 제외한
    모든 테트로미노의 모양을 찾을 수 있고,
    4번 이동하는 모양중에서 하나라도 테트로미노 모양과 다른 경우는 존재하지 않는다.
    그 이유는 상, 하, 좌, 우 탐색으로 테트로미노를 나타낼 수 있기 때문이다.

    반면에 ㅗ, ㅓ, ㅏ, ㅜ 모양의 테트로미노는 갈래길에서 한 곳으로만 갈 수 있으므로 상하좌우 탐색만으로 구할 수 없다.
    따라서 2번째 탐색에서 3번쨰 위치를 방문했다고 가정하고, 현재의 위치(2번째)를 다시한번 탐색하면 해당 테트로미노의 모양을 구할 수 있다.
    예를들어 2번째까지 --  방문했고 3번째도 ---이렇게 탐색해야 한다면 2번째 --에서 3번쨰 --- 위치를 방문했다고 가정하고 2번째 --를 다시 한번 탐색한다.
    그렇게 되면 ㅗ 또는 ㅜ 모양을 구할 수 있다.
*/
import java.util.*;
import java.io.*;

class Boj14500 {
    static int n, m, max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                backTracking(i, j, map[i][j], 1);
                visited[i][j] = false;
            }
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void backTracking(int r, int c, int sum, int count) {
        if (count == 4) {
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dr = r + row[i];
            int dc = c + col[i];

            if (dr < 0 || dr >= n || dc < 0 || dc >= m) {
                continue;
            }

            if (!visited[dr][dc]) {
                // 오직 ㅗ ㅓ ㅏ ㅜ모양을 위한 방문
                // 현재 세번째의 위치는 방문했다고 가정하고 현재 위치를 다시 방문해 ㅗ ㅏ ㅓ ㅜ의 모양을 탐색할 수 있음
                if (count == 2) {
                    visited[dr][dc] = true;
                    backTracking(r, c, sum + map[dr][dc], count + 1);
                    visited[dr][dc] = false;
                }
                // 다른 4개의 테트로미노를 탐색
                visited[dr][dc] = true;
                backTracking(dr, dc, sum + map[dr][dc], count + 1);
                visited[dr][dc] = false;
            }
        }
    }
}