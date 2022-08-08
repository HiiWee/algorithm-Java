package boj;

import java.util.*;
import java.io.*;

/*
    다익스트라 풀이

    먼저 1~n에서 각 노드로 가는 다익스트라를 돌면서 start 노드에 인접한 노드에 도착하면 해당 노드를 second 노드로 저장한다. (이때 경로표도 갱신)
    second 노드를 저장했으므로 이후 second 노드들과 인접하고 방문하지 않은 노드들 중에서 최소 경로가 갱신된다면
    해당 노드로 가는데 거치는 첫번째 노드는 전부 second 노드이므로 경로표를 갱신하고, 해당 노드의 second 노드도 갱신한다.

*/

class Boj1719_1 {
    static List<Node>[] graph;
    static int n, m;
    static int[][] path;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int idx, cost;
        int second;
        Node(int idx, int cost, int second) {
            this.idx = idx;
            this.cost = cost;
            this.second = second;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        path = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, c, -1));
            graph[e].add(new Node(s, c, -1));
        }

        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }
        printArr();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void printArr() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append(path[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
    }

    public static void dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        que.offer(new Node(start, 0, 0));


        while (!que.isEmpty()) {
            Node curNode = que.poll();
            if (curNode.cost > dist[curNode.idx]) {
                continue;
            }

            for (int i = 0; i < graph[curNode.idx].size(); i++) {
                Node nextNode = graph[curNode.idx].get(i);
                if (dist[nextNode.idx] > nextNode.cost + curNode.cost) {
                    dist[nextNode.idx] = nextNode.cost + curNode.cost;

                    // start노드와 인접한 노드라면 해당 노드의 second를 자기자신으로 갱신 후 경로표 수정
                    if (curNode.second == 0) {
                        nextNode.second = nextNode.idx;
                        path[start][nextNode.idx] = nextNode.idx;
                    } else {
                    // start노드와 인접노드 이후의 노드라면 꺼낸 curNode의 second 노드를 그대로 따르고 경로표도 수정한다.
                        nextNode.second = curNode.second;
                        path[start][nextNode.idx] = curNode.second;
                    }
                    que.add(new Node(nextNode.idx, dist[nextNode.idx], nextNode.second));
                }
            }
        }
    }
}

/*
    플로이드 워셜 풀이

    특정 노드에서 다른 노드로 가기위해 제일먼저 들러야 하는 집하장을 구하기 위해선
    특정 노드에서 다른 노드로 가는 최단경로를 구해야 한다.

    플로이드 워셜을 이용하여 from->to or from->middle->to 경로중에서
    더 작은 경로를 선택하여 최소경로를 갱신하고
    갱신될때마다 인접행렬로 구성된 경로표에 들러야 하는 경로를 최신화 하면 된다.

    최소경로는 둘 중에 하나가 된다: from->to 혹은 from->middle->to
    만약 from->to라면 경로표의 from->to는 첫번째로 방문하는 노드가 to가 되는것이고
    from->middle->to가 최소 경로라면, from->to로 가는 경로에서 첫번째 방문노드는 middle이 된다.

*/

class Boj1719_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n + 1][n + 1];
        int[][] path = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(matrix[i], 100000000);
            matrix[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[s][e] = c;
            matrix[e][s] = c;

            path[s][e] = e;
            path[e][s] = s;
        }
        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (matrix[from][to] > matrix[from][middle] + matrix[middle][to]) {
                        matrix[from][to] = matrix[from][middle] + matrix[middle][to];
                        path[from][to] = path[from][middle];
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    sb.append("- ");
                } else {
                    sb.append(path[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
