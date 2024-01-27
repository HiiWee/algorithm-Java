package boj;

import java.io.*;
import java.util.*;

class Boj3980 {

    static int n;
    static int[][] players;
    static int maxSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (n-- > 0) {
            players = new int[11][11];
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    players[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            maxSum = Integer.MIN_VALUE;
            find(new boolean[11], 0, 0);
            result.append(maxSum).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void find(boolean[] visited, int sum, int index) {
        if (index == 11) {
            maxSum = Math.max(sum, maxSum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (!visited[i] && players[i][index] > 0) {
                visited[i] = true;
                find(visited, sum + players[i][index], index + 1);
                visited[i] = false;
            }
        }
    }
}