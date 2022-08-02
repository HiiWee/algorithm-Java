package boj;

/*
    연습한 다익스트라를 이용해 풀어보자
    먼저 각 도시의 최단경로를 다익스트라 알고리즘을 이용해 구해주고

    이후 dist 배열을 탐방하며 최단 경로가 k 값인 경우의 도시를 모두 저장한다.
    없다면 -1을 출력
*/

import java.io.*;
import java.util.*;

class Boj18352 {
    // 정점수, 간선수, 목표거리, 출발노드
    static int n, m, k, x;
    static List<List<Node>> graph = new ArrayList<>();
    static List<Integer> result = new ArrayList<>();

    static class Node {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, 1));
        }

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[x] = 0;
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        que.offer(new Node(x, 0));

        while (!que.isEmpty()) {
            Node currentNode = que.poll();

            if (currentNode.cost > dist[currentNode.idx]) {
                continue;
            }

            for (int i = 0; i < graph.get(currentNode.idx).size(); i++) {
                Node nextNode = graph.get(currentNode.idx).get(i);

                if (dist[nextNode.idx] > currentNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = currentNode.cost + nextNode.cost;
                    que.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                    if (dist[nextNode.idx] == k) {
                        result.add(nextNode.idx);
                    }
                }
            }
        }
        if (result.size() == 0) {
            bw.write("-1\n");
        } else {
            Collections.sort(result);
            for (int node : result) {
                bw.write(node + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}