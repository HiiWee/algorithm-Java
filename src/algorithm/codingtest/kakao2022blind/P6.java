package algorithm.codingtest.kakao2022blind;

/*
    백트래킹을 이용해보자!!
    먼저 상하좌우로 움직이면서 딱 K번 이동할 수 있을때
    K번째 탐방을 완료한곳의 좌표값이 r, c와 같다면 더해온 문자열을 저장한다.
    그렇지 않다면 종료
*/

import java.util.*;

class Solution6 {
    int[] row = {-1, 0, 1, 0};
    int[] col = {0, 1, 0, -1};
    int r, c;
    String[] move = {"u", "r", "d", "l"};
    String result = "";
    String[][] map;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        this.r = r;
        this.c = c;
        map = new String[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], ".");
        }
        map[x - 1][y - 1] = "S";
        map[r - 1][c - 1] = "E";

        StringBuilder sb = new StringBuilder();
        backtracking(x - 1, y - 1, 0, k, sb);

        if (result.length() == 0) {
            return "impossible";
        } else {
            String[] results = result.toString().split(" ");
            System.out.println(results.length);
            Arrays.sort(results);
            return results[0];
        }
    }

    public void backtracking(int r, int c, int depth, int end, StringBuilder sb) {
        if (Math.abs(r + 1 - this.r) + Math.abs(c + 1 - this.c) > end - depth) {
            return;
        }
        if (depth == end) {
            if (map[r][c].equals("E")) {
                if (result.equals("")) {
                    result = sb.toString();
                } else if (result.compareTo(sb.toString()) > 0) {
                    result = sb.toString();
                }
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextR = r + row[i];
            int nextC = c + col[i];

            if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map[0].length) {
                continue;
            }
            sb.append(move[i]);
            backtracking(nextR, nextC, depth + 1, end, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        int[] n = {3, 2, 3};
        int[] m = {4, 2, 3};
        int[] x = {2, 1, 1};
        int[] y = {3, 1, 2};
        int[] r = {3, 2, 3};
        int[] c = {1, 2, 3};
        int[] k = {5, 2, 4};
        String[] results = {"dllrl", "dr", "impossible"};

        Solution6 solution;

        for (int i = 0; i < n.length; i++) {
            solution = new Solution6();
            String result = solution.solution(n[i], m[i], x[i], y[i], r[i], c[i], k[i]);
            System.out.println(result);

            System.out.println();
        }
    }
}