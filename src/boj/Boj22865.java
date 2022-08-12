package boj;

/*
    다익스트라를 이용해 3개의 집을 제외하고 n-3개의 집에 대한 최단경로를 구한다.
    이후 생성된 3개의 dist 그래프를 행렬로 봤을때 3개의 집에 해당되는 열값을 제외한 열에서
     3행중 최소값을 구하고 그 중 가장 큰 값을 구하면 된다.
   */

import java.util.*;
import java.io.*;

class Boj22865 {
    static final long INF = 987654321;
    static int n, m;
    static int[] friend = new int[3];
    static List<Node>[] graph;
    static int[] house;
    static long[][] dist;

    static class Node {
        int idx;
        long cost;

        Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        dist = new long[3][n + 1];
        for (int i = 0; i < 3; i++) {
            friend[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        house = new int[n + 1];
        graph = new ArrayList[n + 1];
        // 인접리스트 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            house[i] = 1;
        }
        // 간선 및 가중치 받아오기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, c));
            graph[e].add(new Node(s, c));
        }

        for (int i = 0; i < 3; i++) {
            // 3개의 친구들 집에서 시작하는 최단경로 각각 구해주기
            dijkstra(i, friend[i]);
        }

        int minNode = -1;
        int maxNode = -1;
        long max = Long.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            long min = Long.MAX_VALUE;

            if (friend[0] == i || friend[1] == i || friend[2] == i) {
                continue;
            }
            for (int j = 0; j < 3; j++) {
                if (min > dist[j][i]) {
                    min = dist[j][i];
                    minNode = i;
                }
            }
            if (max < min) {
                max = min;
                maxNode = minNode;
            }
        }
        bw.write(maxNode + "\n");
        bw.flush();
        bw.close();
    }

    public static void dijkstra(int index, int start) {
        Arrays.fill(dist[index], INF);
        dist[index][start] = 0;
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
        que.add(new Node(start, 0));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (dist[index][curNode.idx] < curNode.cost) {
                continue;
            }

            for (int i = 0; i < graph[curNode.idx].size(); i++) {
                Node nextNode = graph[curNode.idx].get(i);
                if (dist[index][nextNode.idx] > curNode.cost + nextNode.cost) {
                    dist[index][nextNode.idx] = curNode.cost + nextNode.cost;
                    que.add(new Node(nextNode.idx, dist[index][nextNode.idx]));
                }
            }
        }
    }
}