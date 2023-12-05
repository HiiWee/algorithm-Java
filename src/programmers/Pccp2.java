package programmers;

import java.util.*;

public class Pccp2 {

    static class Solution {

        static class Node {
            int r, c;

            Node(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        private final int[] rows = {-1, 0, 1, 0};
        private final int[] cols = {0, 1, 0, -1};

        private int n, m;
        private int[][] map;
        private int[][] visited;
        private Map<Integer, Integer> areaSizes = new HashMap<>();

        public int solution(int[][] land) {
            map = land;
            n = map.length;
            m = map[0].length;
            visited = new int[n][m];

            int areaNumber = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] == 0 && map[i][j] > 0) {
                        int size = bfs(areaNumber, new Node(i, j));
                        areaSizes.put(areaNumber++, size);
                    }
                }
            }

            int maxSum = 0;
            for (int i = 0; i < m; i++) {
                boolean[] areaVisited = new boolean[areaNumber];
                int currentSum = 0;
                for (int j = 0; j < n; j++) {
                    if (map[j][i] == 1 && !areaVisited[visited[j][i]]) {
                        areaVisited[visited[j][i]] = true;
                        currentSum += areaSizes.get(visited[j][i]);
                    }
                }
                maxSum = Math.max(currentSum, maxSum);
            }

            return maxSum;
        }

        public int bfs(int areaNumber, Node startNode) {
            Queue<Node> que = new LinkedList<>();
            visited[startNode.r][startNode.c] = areaNumber;
            que.offer(startNode);

            int size = 1;
            while (!que.isEmpty()) {
                Node curNode = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nextR = curNode.r + rows[i];
                    int nextC = curNode.c + cols[i];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                        continue;
                    }
                    if (visited[nextR][nextC] == 0 && map[nextR][nextC] == 1) {
                        size++;
                        visited[nextR][nextC] = areaNumber;
                        que.offer(new Node(nextR, nextC));
                    }
                }
            }
            return size;
        }
    }
}
