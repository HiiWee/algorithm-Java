package boj;

/*
    오목은 5개가 연속으로 놓인 돌이 존재하면 해당 돌의 승리가 된다.
    오목의 승리 조건을 검사하기 위해선, -, |, /, \ 이렇게 4개의 조건을 검사해야 한다.
    하지만, 문제의 조건에서 연속된 5개의 돌 중에서 가장 왼쪽 돌, 세로라면 가장 위의 돌의 위치를 출력한다.
    따라서, 다음과 같이 돌 5개를 검사한다.
        - : 왼쪽에서 오른쪽으로 검사
        | : 위에서 아래로 검사
        / : 왼쪽아래에서 우측 윗 대각 방향 검사
        \ : 좌측 위 대각에서 우측 아래 대각 방향으로 검사
    이렇게 검사하여 항상 시작지점을 우리가 출력해야 하는 위치로 결정한다.

    따라서 전체 19 x 19 판을 돌며 시작 위치에서 위의 4개 조건을 검사한다.
    만약 승패를 알 수 없는 경우라면 0만 출력하고
    승패가 결정됐다면 해당 돌의 번호를 출력하고 시작 지점을 출력한다.

    오목판에 상하좌우 패딩을 1씩 주어 6목을 검사할때 좀 더 편리하게 사용할 수 있음
*/

import java.util.*;
import java.io.*;

class Main {
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new int[21][21];

        for (int i = 1; i <= 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int winner = 0;
        int r = -1, c = -1;
        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                int find = findWinner(i, j, map[i][j]);
                if (find != 0) {
                    winner = find;
                    r = i;
                    c = j;
                    break;
                }
            }
            if (winner != 0) {
                break;
            }
        }

        if (winner != 0) {
            bw.write(winner + "\n");
            bw.write(r + " " + c);
        } else {
            bw.write(winner + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int findWinner(int r, int c, int symbol) {
        boolean flag = true;
        // --- 검사
        for (int i = 0; i <= 4; i++) {
            if (c + i > 19) {
                flag = false;
                break;
            }
            if (map[r][c + i] != symbol) {
                flag = false;
                break;
            }
        }
        if (flag) {
            // 6목인지 검사
            if (c + 5 <= 20 && map[r][c + 5] != symbol && c - 1 >= 0 && map[r][c - 1] != symbol) {
                return symbol;
            }
        }
        flag = true;

        // | 검사
        for (int i = 0; i <= 4; i++) {
            if (r + i > 19) {
                flag = false;
                break;
            }
            if (map[r + i][c] != symbol) {
                flag = false;
                break;
            }
        }
        if (flag) {

            if (r + 5 <= 20 && map[r + 5][c] != symbol && r - 1 >= 0 && map[r - 1][c] != symbol) {
                return symbol;
            }
        }
        flag = true;

        // / 검사
        for (int i = 0; i <= 4; i++) {
            if (r - i < 1 || c + i > 19) {
                flag = false;
                break;
            }
            if (map[r - i][c + i] != symbol) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (r - 5 >= 0 && c + 5 <= 20 && map[r - 5][c + 5] != symbol && r + 1 <= 20 && c - 1 >= 0 && map[r + 1][c - 1] != symbol) {
                return symbol;
            }
        }
        flag = true;

        // \ 검사
        for (int i = 0; i <= 4; i++) {
            if (r + i > 19 || c + i > 19) {
                flag = false;
                break;
            }
            if (map[r + i][c + i] != symbol) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (r + 5 <= 20 && c + 5 <= 20 && map[r + 5][c + 5] != symbol && r - 1 >= 0 && c - 1 >= 0 && map[r - 1][c - 1] != symbol) {
                return symbol;
            }
        }
        return 0;
    }
}

