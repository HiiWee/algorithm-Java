package boj;

import java.io.*;
import java.util.*;

class Boj16956 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int r, c;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        Queue<Node> que = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'W') {
                    que.offer(new Node(i, j));
                }
            }
        }

        boolean canMeet = false;
        while (!que.isEmpty() && !canMeet) {
            Node node = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = node.r + rows[i];
                int nextC = node.c + cols[i];

                if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                    continue;
                }

                if (map[nextR][nextC] == 'S') {
                    canMeet = true;
                    break;
                }
                if (map[nextR][nextC] == '.') {
                    map[nextR][nextC] = 'D';
                }
            }
        }

        if (canMeet) {
            bw.write("0");
        } else {
            StringBuilder answer = new StringBuilder();
            answer.append("1\n");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    answer.append(map[i][j]);
                }
                answer.append("\n");
            }

            bw.write(answer.toString());
        }
        bw.flush();
        bw.close();
    }
}
