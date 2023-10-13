package boj;

import java.io.*;
import java.util.*;

class Boj4179 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int n, m, fastestSecond = Integer.MAX_VALUE;
    private static char[][] map;
    private static int[][] escapeCounts;
    private static int[][] fireCounts;

    private static Node startNode;
    private static Queue<Node> fires = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        escapeCounts = new int[n][m];
        fireCounts = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(fireCounts[i], -1);
            Arrays.fill(escapeCounts[i], -1);
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    startNode = new Node(i, j);
                } else if (map[i][j] == 'F') {
                    fireCounts[i][j] = 0;
                    fires.offer(new Node(i, j));
                }
            }
        }

        // 1. 불길을 먼저 번지게함
        fireBfs();

        // 2. 사람이 탈출할 수 있는 모든 경로를 구하면서 불길이 도착하는 시간보다 하나라도 빠르다면 탈출가능
        boolean canEscape = escapeBfs();

        // 출구가 막혀있거나, 불길에 막혀 탈출할 수 없는 경우
        if (!canEscape || fastestSecond == Integer.MAX_VALUE) {
            bw.write("IMPOSSIBLE");
        } else {
            bw.write(fastestSecond + "");
        }
        bw.flush();
        bw.close();
    }

    public static void fireBfs() {
        while (!fires.isEmpty()) {
            Node curNode = fires.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }
                if (fireCounts[nextR][nextC] == -1 && map[nextR][nextC] == '.') {
                    fireCounts[nextR][nextC] = fireCounts[curNode.r][curNode.c] + 1;
                    fires.offer(new Node(nextR, nextC));
                }
            }
        }
    }

    public static boolean escapeBfs() {
        boolean canEscape = false;
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);
        escapeCounts[startNode.r][startNode.c] = 0;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    canEscape = true;
                    if (fireCounts[curNode.r][curNode.c] == -1 || escapeCounts[curNode.r][curNode.c] < fireCounts[curNode.r][curNode.c])
                        fastestSecond = Math.min(fastestSecond, escapeCounts[curNode.r][curNode.c] + 1);
                    continue;
                }
                if (escapeCounts[nextR][nextC] == -1 && map[nextR][nextC] == '.') {
                    escapeCounts[nextR][nextC] = escapeCounts[curNode.r][curNode.c] + 1;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
        return canEscape;
    }
}