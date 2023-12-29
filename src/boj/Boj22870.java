package boj;

import java.io.*;
import java.util.*;

class Boj22870 {

    static class Node {
        int number, dist;

        public Node(int number, int dist) {
            this.number = number;
            this.dist = dist;
        }
    }

    static int n, m, s, e;
    static List<Node>[] graphs;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graphs = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graphs[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graphs[s].add(new Node(e, d));
            graphs[e].add(new Node(s, d));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        // 시작 -> 끝 최단 경로 탐색
        int[] dist = searchPath(s);
        // 끝 -> 시작 최단 경로 탐색
        int[] backDist = searchPath(e);
        // 사전순 가장 빠른 최단경로 찾기
        findRoute(dist, backDist);
        backDist = searchPath(e);
        bw.write((dist[e] + backDist[s]) + "");
        bw.flush();
        bw.close();
    }

    private static void findRoute(final int[] dist, final int[] backDist) {
        int start = s;
        while (start != e) {
            int minNodeNumber = Integer.MAX_VALUE;
            for (Node nextNode : graphs[start]) {
                // 시작노드까지의 최단경로 + 다음 노드의 값 + 끝지점부터 다음노드까지의 최단 경로가 시작 ~ 도착까지의 최단경로 길이와 같아야 함
                if (dist[start] + nextNode.dist + backDist[nextNode.number] == dist[e] && !visited[nextNode.number]) {
                    minNodeNumber = Math.min(minNodeNumber, nextNode.number);
                }
            }
            visited[minNodeNumber] = true;
            start = minNodeNumber;
        }
    }

    public static int[] searchPath(int s) {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
        que.offer(new Node(s, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (Node next : graphs[curNode.number]) {
                int nextDistance = next.dist + curNode.dist;

                if (nextDistance < dist[next.number] && !visited[next.number]) {
                    dist[next.number] = nextDistance;
                    que.offer(new Node(next.number, nextDistance));
                }
            }
        }
        return dist;
    }
}