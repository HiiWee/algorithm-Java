package boj;

/*
    완전탐색을 통해 만들 수 있는 모든 체스판을 만들어야 한다.
    체스판의 시작점인 좌측 맨위의 점을 각 판들의 점으로 두고 8 * 8의 체스판을 만들 수 있는지 검사하고
    만들 수 있다면 시작이 W, B인 체스판 2개를 비교해 그 중 최소의 색깔변경횟수를 갖는 값을 저장한다.

    이를 8*8을 만들 수 있는 모든점에서 검사해야 한다.
*/
import java.io.*;
import java.util.*;

class Boj1018 {
    static int n, m;
    static char[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i + 8 > n) {
                break;
            }
            for (int j = 0; j < m; j++) {
                if (j + 8 > m) {
                    break;
                }
                min = Math.min(getMinCountToPaint(i, j, 'W'), min);
                min = Math.min(getMinCountToPaint(i, j, 'B'), min);
            }
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();

    }

    public static int getMinCountToPaint(int r, int c, char color) {
        int count = 0;
        for (int i = r; i < r + 8; i++) {
            for (int j = c; j < c +8; j++) {
                if (color != map[i][j]) {
                    count++;
                }
                color = getFlipColor(color);
            }
            color = getFlipColor(color);
        }
        return count;
    }

    public static char getFlipColor(char color) {
        if (color == 'W') {
            return 'B';
        } else {
            return 'W';
        }
    }
}