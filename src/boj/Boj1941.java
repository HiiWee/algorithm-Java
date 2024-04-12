package boj;

import java.io.*;
import java.util.*;

class Boj1941 {

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int groupCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int[] answer = new int[7];
        search(0, 0, new int[7]);

        bw.write(Integer.toString(groupCount));
        bw.flush();
        bw.close();
    }

    public static void search(int current, int count, int[] answers) {
        if (count == 7) {
            if (areaCheck(answers)) {
                groupCount++;
            }
            return;
        }

        for (int i = current; i < 25; i++) {
            visited[i / 5][i % 5] = true;
            answers[count] = i;
            search(i + 1, count + 1, answers);
            visited[i / 5][i % 5] = false;
        }
    }

    public static boolean areaCheck(int[] answers) {
        int dasomCount = 0;
        for (int answer : answers) {
            if (map[answer / 5][answer % 5] == 'S') {
                dasomCount++;
            }
        }
        if (dasomCount < 4) {
            return false;
        }

        boolean[][] currentVisited = new boolean[5][5];
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{answers[0] / 5, answers[0] % 5});
        currentVisited[answers[0] / 5][answers[0] % 5] = true;
        int count = 1;

        while (!que.isEmpty()) {
            int[] current = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = current[0] + rows[i];
                int nextC = current[1] + cols[i];

                if (nextR < 0 || nextR >= 5 || nextC < 0 || nextC >= 5) {
                    continue;
                }

                if (visited[nextR][nextC] && !currentVisited[nextR][nextC]) {
                    currentVisited[nextR][nextC] = true;
                    count++;
                    que.offer(new int[]{nextR, nextC});
                }
            }
        }
        return count == 7;
    }
}
