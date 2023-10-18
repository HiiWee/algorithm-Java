package boj;

import java.io.*;
import java.util.*;

class Boj16932 {

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int n, m;
    private static int[][] map;
    private static int[][] groupMap;
    private static List<Integer> groups = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        groupMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        groups.add(0);
        int groupCount = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && groupMap[i][j] == 0) {
                    int groupSize = bfs(new Node(i, j), groupCount++);
                    groups.add(groupSize);
                }
            }
        }

        int maxSize = 0;
        Set<Integer> duplicateGroups = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int size = 1;
                duplicateGroups.clear();

                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nextR = i + rows[k];
                        int nextC = j + cols[k];

                        if (isInvalidPos(nextR, nextC)) {
                            continue;
                        }
                        if (map[nextR][nextC] == 1) {
                            if (!duplicateGroups.contains(groupMap[nextR][nextC])) {
                                size += groups.get(groupMap[nextR][nextC]);
                                duplicateGroups.add(groupMap[nextR][nextC]);
                            }
                        }
                    }
                }
                maxSize = Math.max(size, maxSize);
            }
        }

        bw.write(maxSize + "");
        bw.flush();
        bw.close();
    }

    public static int bfs(Node startNode, int groupCount) {
        groupMap[startNode.r][startNode.c] = groupCount;
        int count = 1;

        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (isInvalidPos(nextR, nextC)) {
                    continue;
                }

                if (map[nextR][nextC] == 1 && groupMap[nextR][nextC] == 0) {
                    groupMap[nextR][nextC] = groupMap[curNode.r][curNode.c];
                    que.offer(new Node(nextR, nextC));
                    count++;
                }
            }
        }
        return count;
    }


    public static boolean isInvalidPos(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m;
    }
}