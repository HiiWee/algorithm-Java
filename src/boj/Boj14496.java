package boj;

import java.io.*;
import java.util.*;

class Boj14496 {

    static class Node {
        int number, cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    static int a, b;
    static int n, m;
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        int answer = search();

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }

    public static int search() {
        boolean[] visited = new boolean[n + 1];

        Queue<Node> que = new ArrayDeque<>();
        visited[a] = true;
        que.offer(new Node(a, 0));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.number == b) {
                return curNode.cost;
            }
            for (int next : graph[curNode.number]) {
                if (!visited[next]) {
                    visited[next] = true;
                    que.offer(new Node(next, curNode.cost + 1));
                }
            }
        }

        return -1;
    }
}

