package boj;

/*
    다익스트라를 이용해 풀어보자!!
*/

import java.util.*;
import java.io.*;

class Boj1916 {
    static int v, e, start, end;
    static List<Node>[] graph;
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
        v = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());
        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int[] dist = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {
            Node currentNode = que.poll();

            // 큐에 들어간 노드는 거리가 이미 최소값임이 보증이 된 노드이다.
            // 매 순간 최소 경로의 노드를 선택하여 계산하였으므로, 만약 큐에 들어간 노드보다 최소경로인 노드가 나타나면
            // 그 말은 애초에 다익스트라 알고리즘에서 그리디 적으로 최소 비용을 선택하며 진행하지 않았다는 의미가 된다.
            if (currentNode.idx == end) {
                break;
            }

            if (currentNode.cost > dist[currentNode.idx]) {
                continue;
            }

            for (Node nextNode : graph[currentNode.idx]) {
                if (dist[nextNode.idx] > currentNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = currentNode.cost + nextNode.cost;
                    que.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        bw.write(dist[end] + "\n");
        bw.flush();
        bw.close();
    }
}