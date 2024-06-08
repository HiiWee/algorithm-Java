package boj;

import java.io.*;

class Boj2239 {

    static int[][] map = new int[9][9];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        search(0, 0);
    }

    public static void search(int r, int c) {
        if (c == 9) {
            r++;
            c = 0;
        }

        if (r == 9) {
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int number : map[i]) {
                    answer.append(number);
                }
                answer.append("\n");
            }
            System.out.print(answer);
            System.exit(0);
        }

        if (map[r][c] != 0) {
            search(r, c + 1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (isPossible(i, r, c)) {
                map[r][c] = i;
                search(r, c + 1);
                map[r][c] = 0;
            }
        }
    }

    public static boolean isPossible(int number, int r, int c) {
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == number || map[i][c] == number) {
                return false;
            }
        }
        for (int i = r / 3 * 3; i < r / 3 * 3 + 3; i++) {
            for (int j = c / 3 * 3; j < c / 3 * 3 + 3; j++) {
                if (map[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }
}
