package boj;

import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static List<Integer>[] friends;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        friends = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            friends[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }
        bfs();

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        int[] visited = new int[n + 1];
        que.offer(1);
        visited[1] = 1;

        while (!que.isEmpty()) {
            int current = que.poll();

            for (int next : friends[current]) {
                if (visited[next] == 0) {
                    visited[next] = visited[current] + 1;
                    if (visited[next] <= 3) {
                        count++;
                    }
                    que.offer(next);
                }
            }
        }
    }
}