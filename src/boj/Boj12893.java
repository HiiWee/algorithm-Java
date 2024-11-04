package boj;

import java.io.*;
import java.util.*;

class Boj12893 {

    static int n, m;
    static List<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new int[n + 1];
        Arrays.fill(visited, -1);

        boolean answer = true;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == -1) {
                if (!search(i)) {
                    answer = false;
                    break;
                }
            }
        }

        bw.write(answer ? "1" : "0");
        bw.flush();
        bw.close();
    }

    public static boolean search(int number) {
        visited[number] = 0;
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(number);
        boolean isPossible = true;

        while (!que.isEmpty() && isPossible) {
            int current = que.poll();

            for (int next : graph[current]) {
                int nextFlag = (visited[current] + 1) % 2;

                if (visited[next] != -1) {
                    if (visited[next] != nextFlag) {
                        isPossible = false;
                        break;
                    }
                } else {
                    visited[next] = nextFlag;
                    que.offer(next);
                }
            }
        }

        return isPossible;
    }
}
