package boj;

import java.io.*;
import java.util.*;

class Boj7562 {

    static final int[] rows = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] cols = {-2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int l = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int fromR = Integer.parseInt(st.nextToken());
            int fromC = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int toR = Integer.parseInt(st.nextToken());
            int toC = Integer.parseInt(st.nextToken());

            int count = search(fromR, fromC, toR, toC, new int[l][l]);

            answer.append(count).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static int search(int r, int c, int toR, int toC, int[][] map) {
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{r, c});
        map[r][c] = 1;

        while (!que.isEmpty()) {
            int[] current = que.poll();

            if (current[0] == toR && current[1] == toC) {
                return map[current[0]][current[1]] - 1;
            }

            for (int i = 0; i < 8; i++) {
                int nextR = current[0] + rows[i];
                int nextC = current[1] + cols[i];

                if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map.length) {
                    continue;
                }

                if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = map[current[0]][current[1]] + 1;
                    que.offer(new int[]{nextR, nextC});
                }
            }
        }

        return -1;
    }
}

