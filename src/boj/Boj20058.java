package boj;

import java.io.*;
import java.util.*;

class Boj20058 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static int n, q, powN;
    private static int[][] map;

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        powN = (int) Math.pow(2, n);
        q = Integer.parseInt(st.nextToken());
        map = new int[powN][powN];

        for (int i = 0; i < powN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < powN; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (q-- > 0) {
            int l = Integer.parseInt(st.nextToken());
            spinMap(l);
            decreaseIce();
        }

        int totalIce = getTotalIce();
        int maxChunkSize = getMaxChunkSize();

        bw.write(totalIce + "\n" + maxChunkSize);
        bw.flush();
        bw.close();
    }

    public static int getMaxChunkSize() {
        int size = 0;
        boolean[][] visited = new boolean[powN][powN];
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    size = Math.max(size, bfs(new Node(i, j), visited));
                }
            }
        }
        return size;
    }

    public static int bfs(Node startNode, boolean[][] visited) {
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);
        visited[startNode.r][startNode.c] = true;
        int size = 1;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= powN || nextC < 0 || nextC >= powN) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC] > 0) {
                    size++;
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
        return size;
    }

    public static int getTotalIce() {
        int sum = 0;
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }

    public static void decreaseIce() {
        int[][] newMap = new int[powN][powN];
        for (int i = 0; i < powN; i++) {
            for (int j = 0; j < powN; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nextR = i + rows[k];
                    int nextC = j + cols[k];

                    if (nextR < 0 || nextR >= powN || nextC < 0 || nextC >= powN) {
                        count++;
                        continue;
                    }

                    if (map[nextR][nextC] == 0) {
                        count++;
                    }
                }
                if (count > 1) {
                    newMap[i][j] = Math.max(0, map[i][j] - 1);
                } else {
                    newMap[i][j] = map[i][j];
                }
            }
        }
        map = newMap;
    }

    public static void spinMap(int l) {
        int[][] copyMap = new int[powN][powN];

        int targetLength = (int) Math.pow(2, l);
        for (int i = 0; i < powN; i += targetLength) {
            for (int j = 0; j < powN; j += targetLength) {
                int mapRow = i;
                int mapCol = j;
                for (int k = j; k < j + targetLength; k++) {
                    for (int h = i + targetLength - 1; h >= i; h--) {
                        copyMap[mapRow][mapCol++] = map[h][k];
                    }
                    mapCol = j;
                    mapRow++;
                }
            }
        }
        map = copyMap;
    }
}