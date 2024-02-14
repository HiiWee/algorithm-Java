package programmers;

// 플로이드 워셜
import java.util.*;

class 합승_택시_요금1 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], 1000000);
            graph[i][i] = 0;
        }
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

        for (int m = 1; m <= n; m++) {
            for (int f = 1; f <= n; f++) {
                for (int t = 1; t <= n; t++) {
                    if (graph[f][t] > graph[f][m] + graph[m][t]) {
                        graph[f][t] = graph[f][m] + graph[m][t];
                    }
                }
            }
        }
        // 2. S -> 다른지점, 다른지점 -> A, 다른지점 -> B로 가는 경우
        // (다른지점에 S도 포함되므로 모든 경우 탐색 할 수 있음)
        for (int i = 1; i <= n; i++) {
            answer = Math.min(graph[s][i] + graph[i][a] + graph[i][b], answer);
        }
        return answer;
    }
}
// 다익스트라
class 합승_택시_요금2 {

    static class Node {
        int number, cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    List<Node>[] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향 그래프 구성
        for (int[] fare : fares) {
            graph[fare[0]].add(new Node(fare[1], fare[2]));
            graph[fare[1]].add(new Node(fare[0], fare[2]));
        }

        // A, B, S에서 시작해서 각 정점으로 가는 최단 거리 구하기
        int[] aStartDist = dijkstra(a, new int[n + 1]);
        int[] bStartDist = dijkstra(b, new int[n + 1]);
        int[] sStartDist = dijkstra(s, new int[n + 1]);

        // A, B, S에서 임의의 정점 i까지로 도착하는 최단 거리의 합 중에서 최소 구하기
        for (int i = 1; i <= n; i++) {
            answer = Math.min(aStartDist[i] + bStartDist[i] + sStartDist[i], answer);
        }
        return answer;
    }

    public int[] dijkstra(int start, int[] dist) {
        Arrays.fill(dist, 1000000);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (dist[current.number] < current.cost) {
                continue;
            }

            for (Node next : graph[current.number]) {

                if (dist[next.number] > current.cost + next.cost) {
                    dist[next.number] = current.cost + next.cost;
                    pq.offer(new Node(next.number, dist[next.number]));
                }
            }
        }
        return dist;
    }
}