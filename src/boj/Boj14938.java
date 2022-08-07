package boj;

/*
    다익스트라, 플로이드 워셜 모두 가능하다.
    1. 다익스트라
        시작 지역에서 다른지역으로 갈 수 있는 최소값을 구하되 수색범위 이하의 값만 탐방하여 후에 dist배열에서
        갈 수 있는 지역으로 선정되었다면 해당 지역의 아이템의 개수를 더한다.
        이후 시작지역을 변경하며 최대값을 갱신해간다.

   2. 플로이드 워셜
       일단 전체 경로를 인접 행렬로 받아 각 경로의 최소값을 모두 구하고
       시작 지점에서 각 지역 기준으로 이동할 수 있는 최소 경로가 수색범위 이하라면 아이템을 더해준다.
        플로이드를 구하면 각 지역에서 갈 수 있는 모든 최소값을 한번에 구하므로 좀 더 적합해보임
*/

import java.util.*;
import java.io.*;

// 다익스트라 풀이
class Boj14938_1 {
    static int n;
    static int limit;
    static int[] item;
    static List<Node>[] adjList;
    static int max = Integer.MIN_VALUE;
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
        limit = Integer.parseInt(st.nextToken());
        int road = Integer.parseInt(st.nextToken());
        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < road; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[s].add(new Node(e, c));
            adjList[e].add(new Node(s, c));
        }

        for (int i = 1; i <= n; i++) {
            int[] dist = dijkstra(i);
            max = Math.max(max, getSum(dist));
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        dist[start] = 0;
        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.cost > dist[curNode.idx] || curNode.cost > limit) {
                continue;
            }

            for (int i = 0; i < adjList[curNode.idx].size(); i++) {
                Node nextNode = adjList[curNode.idx].get(i);
                if (dist[nextNode.idx] > curNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    que.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        return dist;
    }

    public static int getSum(int[] dist) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= limit) {
                sum += item[i];
            }
        }
        return sum;
    }
}

// 플로이드 워셜 풀이
class Boj14938_2 {
    static int n, limit;
    static int[][] map;
    static int[] item;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());
        int road = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(map[i], 3000);
            map[i][i] = 0;
        }

        for (int i = 0; i < road; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[s][e] = c;
            map[e][s] = c;
        }

        floyd();

        for (int i = 1; i <= n; i++) {
            max = Math.max(max, getSum(i));
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static int getSum(int start) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (map[start][i] <= limit) {
                sum += item[i];
            }
        }
        return sum;
    }

    public static void floyd() {
        for (int middle = 0; middle <= n; middle++) {
            for (int from = 0; from <= n; from++) {
                for (int to = 0; to <= n; to++) {
                    map[from][to] = Math.min(map[from][to], map[from][middle] + map[middle][to]);
                }
            }
        }
    }

}