package leetcode;

import java.util.*;

class LeetCode36 {

    static int[][] FIXED_POINT = {{0, 0}, {0, 3}, {0, 6}, {3, 0}, {3, 3}, {3, 6}, {6, 0}, {6, 3}, {6, 6}};

    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[10];

        for (int k = 0; k < 9; k++) {
            int startR = FIXED_POINT[k][0];
            int startC = FIXED_POINT[k][1];

            for (int i = startR; i < startR + 3; i++) {
                for (int j = startC; j < startC + 3; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    int number = board[i][j] - '0';
                    if (visited[number]) {
                        return false;
                    }
                    visited[number] = true;
                }
            }
            Arrays.fill(visited, false);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int number = board[i][j] - '0';
                if (visited[number]) {
                    return false;
                }
                visited[number] = true;


            }
            Arrays.fill(visited, false);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                int number = board[j][i] - '0';
                if (visited[number]) {
                    return false;
                }
                visited[number] = true;
            }
            Arrays.fill(visited, false);
        }
        return true;
    }
}
