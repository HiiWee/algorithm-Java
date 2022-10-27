package boj;

/*
    1. 모든빈칸C3으로 3개의 울타리를 놓을 곳을 임의로 정한다.
    2. 정한 빈킨을 채우고, BFS를 통해 바이러스의 전염이 시작됨
    3. 모든 BFS가 끝나게 되면 0의 개수를 카운팅한다.
*/

import java.io.*;
import java.util.*;

class Boj14502 {
    static int n, m;
    static int[][] map;

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int zeroCount;
    static int maxCount = Integer.MIN_VALUE;
    static List<Node> sections = new ArrayList<>();
    static List<Node> virus = new ArrayList<>();
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    zeroCount++;
                    sections.add(new Node(i, j));
                } else if (map[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }
        combination(new boolean[sections.size()], sections.size(), 3, 0);

        bw.write(maxCount + "\n");
        bw.flush();
        bw.close();
    }

    // 조합을 통해 모든 벽을 지을 수 있는 조합을 구한다.
    public static void combination(boolean[] visited, int n, int r, int depth) {
        if (r == 0) {
            BFS(visited);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, i + 1);
            visited[i] = false;
        }
    }

    public static void BFS(boolean[] visited) {
        // 3개의 벽 세우기
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                Node node = sections.get(i);
                map[node.r][node.c] = 1;
            }
        }

        // BFS 탐방으로 바이러스 전염 시작
        boolean[][] check = new boolean[n][m];
        int count = 0;
        for (Node node : virus) {
            Queue<Node> que = new LinkedList<>();
            que.add(node);
            while (!que.isEmpty()) {
                Node cur = que.poll();

                for (int i = 0; i < 4; i++) {
                    int nextR = cur.r + row[i];
                    int nextC = cur.c + col[i];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                        continue;
                    }

                    if (map[nextR][nextC] == 0 && !check[nextR][nextC]) {
                        check[nextR][nextC] = true;
                        que.add(new Node(nextR, nextC));
                        count++;
                    }
                }
            }
        }

        // 최대 0의 개수 갱신 : 전체 0의 개수 - 전엽된 수 - 새로새운 벽 3개
        maxCount = Math.max(maxCount, zeroCount - count - 3);

        // 다른 벽을 위해 벽 허물기
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                Node node = sections.get(i);
                map[node.r][node.c] = 0;
            }
        }
    }
}