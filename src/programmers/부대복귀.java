package programmers;

// 다익스트라 풀이
import java.util.*;

class 부대복귀_1 {

    static final int INF = 300_000_000;

    static class Node implements Comparable<Node> {
        int number, dist;

        public Node(int number, int dist) {
            this.number = number;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return dist - node.dist;
        }
    }

    int n;
    List<Integer>[] graph;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        this.n = n;
        graph = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        int[] answer = new int[sources.length];
        int[] dist = dijkstra(destination);
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] == INF) {
                answer[i] = -1;
            } else {
                answer[i] = dist[sources[i]];
            }
        }
        return answer;
    }

    public int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int next : graph[node.number]) {

                if (dist[next] > dist[node.number] + 1) {
                    dist[next] = dist[node.number] + 1;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }
}

// BFS 풀이
class 부대복귀_2 {

    static final int INF = 300_000_000;

    int n;
    List<Integer>[] graph;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        this.n = n;
        graph = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        int[] answer = new int[sources.length];
        int[] dist = bfs(destination);


        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] == INF) {
                answer[i] = -1;
            } else {
                answer[i] = dist[sources[i]];
            }
        }
        return answer;
    }

    public int[] bfs(int start) {
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        dist[start] = 0;
        visited[start] = true;

        while (!que.isEmpty()) {
            int current = que.poll();

            for (int next : graph[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[current] + 1;
                    que.offer(next);
                }
            }
        }

        return dist;
    }
}