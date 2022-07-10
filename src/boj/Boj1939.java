package boj;

import java.util.*;
import java.io.*;

// 이분탐색 + DFS, 로직은 BFS와 동일
class Boj1939_1 {
    static class Node {
        int end;
        int weight;
        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static int n, m, s, e;
    static List<Node>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int weight = upperBound();

        bw.write(--weight + "\n");
        bw.flush();
        bw.close();
    }

    public static int upperBound() {
        int low = 1;
        int high = 1000000001;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            visited = new boolean[n + 1];

            if (!BFS(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static boolean BFS(int mid) {
        Queue<Integer> que = new LinkedList<>();
        que.add(s);
        visited[s] = true;

        while (!que.isEmpty()) {
            int node = que.poll();

            if (node == e) {
                return true;
            }

            for (Node nextNode : list[node]) {
                // 방문하지 않은 섬이고, 현재 mid값을 건널 수 있는 다리의 한계 중량이라면 참
                if (!visited[nextNode.end] && nextNode.weight >= mid) {
                    que.add(nextNode.end);
                    visited[nextNode.end] = true;
                }
            }
        }
        return false;
    }
}

// 최대 중량을 구하고, BFS로 원하는 섬의 공장까지 갈 수 있는지 탐방 갈 수 있다면 증가(UpperBound)
class Boj1939_2 {
    static class Node {
        int end;
        int weight;
        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static int n, m, s, e;
    static List<Node>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
            list[end].add(new Node(start, weight));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int weight = upperBound();

        bw.write(--weight + "\n");
        bw.flush();
        bw.close();
    }

    public static int upperBound() {
        int low = 1;
        int high = 1000000001;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            visited = new boolean[n + 1];

            if (!DFS(mid, s)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static boolean DFS(int mid, int next) {
        if (next == e) {
            return true;
        }
        visited[next] = true;
        for (Node node : list[next]) {
            if (!visited[node.end] && node.weight >= mid) {
                if (DFS(mid, node.end)) {
                    return true;
                }
            }
        }
        return false;
    }
}
