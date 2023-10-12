package boj;

/*
    불행도는 샘터와 집의 거리에 비례한다.
    문제의 목적은 모든 집을 지었을때 불행도의 합이 최소가 되어야 한다.
    즉, 샘터와 항상 제일 가깝게 집을 지어야 하고, 각 샘터 주의에 집이 연속적으로 지어질 수 밖에 없다.
    주어진 집을 각 샘터 근처에 순차적으로 짓게 되면 자연스럽게 최소 거리가 된다.
*/
import java.io.*;
import java.util.*;

class Boj18513 {
    private static final int MIN = -100_000_000;
    private static final int MAX = 100_000_000;
    private static final int[] moves = {1, -1};

    private static final Queue<Node> que = new LinkedList<>();
    private static final Set<Integer> visited = new HashSet<>();

    static class Node {
        int x, distance;

        Node (int x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }
    private static int n, k;
    private static long minDistance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            que.offer(new Node(x, 0));
            visited.add(x);
        }

        bfs();

        bw.write(minDistance + "");
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        int buildCount = 0;
        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 2; i++) {
                int nextX = curNode.x + moves[i];

                if (nextX > MAX || nextX < MIN) {
                    continue;
                }
                if (!visited.contains(nextX)) {
                    buildCount++;
                    minDistance += curNode.distance + 1L;
                    if (buildCount == k) {
                        return;
                    }
                    visited.add(nextX);
                    que.offer(new Node(nextX, curNode.distance + 1));
                }
            }
        }
    }
}