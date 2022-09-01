package boj;

/*
    1번 웜 바이러스
*/
import java.util.*;
import java.io.*;

class Boj2606 {
    static int n, m;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        BFS(1);

        int count = -1;
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS(int start) {
        Queue<Integer> que = new LinkedList<>();
        visited[start] = true;
        que.add(start);

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : graph[cur]) {

                if (!visited[next]) {
                    visited[next] = true;
                    que.add(next);
                }
            }
        }
    }

}