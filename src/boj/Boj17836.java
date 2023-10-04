package boj;

/*
    BFS를 통해 전체 탐색을 한다.
    하면서 검을 만나는 경우가 있다면 flag를 통해 체크함

    1. 검을 만났다면 검부터 종점까지의 두 점 사이의 거리를 구함
       이후 종점까지의 그냥 BFS 거리와 검을 거쳤을때의 거리를 비교하여 더 작은 값을 출력
    2. 검을 만나지 못했다면 종점까지의 그냥 BFS거리를 출력함

    1.을 할때 종점 BFS거리가 구해지지 않았다면 검을 이용한 거리 출력
      검도 못만나고 종점BFS거리가 구해지지 않았다면 Fail출력
    2.에서도 종점까지의 거리를 구하지 못했다면 Fail출력
*/

import java.io.*;
import java.util.*;

class Boj17836 {

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
    private static int[][] counts;
    private static Node magicSwordNode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        counts = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(counts[i], -1);
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        int minTime;
        if (counts[n - 1][m - 1] == -1) {
            counts[n - 1][m - 1] = Integer.MAX_VALUE;
        }
        if (magicSwordNode != null) {
            int swordDistance = counts[magicSwordNode.r][magicSwordNode.c] + getManhattanDistance(magicSwordNode);
            minTime = Math.min(counts[n - 1][m - 1], swordDistance);
        } else {
            minTime = counts[n - 1][m - 1];
        }

        if (minTime == Integer.MAX_VALUE || minTime > t) {
            bw.write("Fail");
        } else {
            bw.write(minTime + "");

        }
        bw.flush();
        bw.close();
    }

    public static int getManhattanDistance(Node node) {
        return Math.abs(node.r - (n - 1)) + Math.abs(node.c - (m - 1));
    }

    public static void bfs() {
        counts[0][0] = 0;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(0, 0));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (map[curNode.r][curNode.c] == 2) {
                    magicSwordNode = new Node(curNode.r, curNode.c);
                }
                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    continue;
                }
                if (counts[nextR][nextC] == -1 && map[nextR][nextC] != 1) {
                    counts[nextR][nextC] = counts[curNode.r][curNode.c] + 1;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
    }
}