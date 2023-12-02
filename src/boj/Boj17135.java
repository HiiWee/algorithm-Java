package boj;

import java.io.*;
import java.util.*;

class Boj17135 {

    // 좌 -> 상 -> 우로 좌측것을 항상 먼저 찾게함
    private static final int[] rows = {0, -1, 0};
    private static final int[] cols = {-1, 0, 1};

    private static int n, m, d, minRow = Integer.MAX_VALUE;
    private static int removeCount;
    private static int[][] map;
    private static int[] archers = new int[3];

    static class Node {
        int r, c, d;

        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                }
            }
        }

        combinations(m, 0, 0);

        bw.write(removeCount + "");
        bw.flush();
        bw.close();
    }

    public static void combinations(int n, int count, int index) {
        if (count == 3) {
            // 뽑은 궁수로 게임 진행
            playDefense();
            return;
        }
        for (int i = index; i < n; i++) {
            archers[count] = i;
            combinations(n, count + 1, i + 1);
        }
    }

    public static void playDefense() {
        int[][] currentMap = new int[n + 1][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                currentMap[i][j] = map[i][j];
            }
        }
        int currentRemoveCount = 0;
        for (int time = 0; time < n; time++) {
            List<Node> findEnemies = new ArrayList<>();
            for (int archer : archers) {
                if (currentMap[n - 1 - time][archer] == 1) {
                    findEnemies.add(new Node(n - 1 - time, archer, 1));
                    continue;
                }
                Queue<Node> que = new LinkedList<>();
                que.offer(new Node(n - 1 - time, archer, 1));

                while (!que.isEmpty()) {
                    Node curNode = que.poll();

                    if (currentMap[curNode.r][curNode.c] == 1) {
                        findEnemies.add(curNode);
                        break;
                    }
                    for (int i = 0; i < 3; i++) {
                        int nextR = curNode.r + rows[i];
                        int nextC = curNode.c + cols[i];

                        if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                            continue;
                        }

                        if (curNode.d < d) {
                            que.offer(new Node(nextR, nextC, curNode.d + 1));
                        }
                    }
                }
            }
            // 화살맞은 적 제거
            int removeCount = 0;
            for (Node findEnemy : findEnemies) {
                if (currentMap[findEnemy.r][findEnemy.c] != 0) {
                    currentMap[findEnemy.r][findEnemy.c] = 0;
                    removeCount++;
                }
            }
            currentRemoveCount += removeCount;
        }
        removeCount = Math.max(removeCount, currentRemoveCount);
    }
}