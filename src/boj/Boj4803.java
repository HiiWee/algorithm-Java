package boj;

import java.io.*;
import java.util.*;

class Bpj4803 {

    static boolean isTree = true;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();
        int testCount = 0;
        while (true) {
            testCount++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            visited = new boolean[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            if (n == 0 && m == 0) {
                break;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b);
                graph[b].add(a);
            }

            int treeCount = 0;
            for (int i = 1; i <= n; i++) {
                isTree = true;
                if (!visited[i]) {
                    search(i);
                    if (isTree) {
                        treeCount++;
                    }
                }
            }
            answer.append("Case ").append(testCount).append(": ");
            if (treeCount == 0) {
                answer.append("No trees.\n");
            } else if (treeCount == 1) {
                answer.append("There is one tree.\n");
            } else {
                answer.append("A forest of ").append(treeCount).append(" trees.\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int number) {
        visited[number] = true;

        int count = 0;
        for (int next : graph[number]) {
            if (!visited[next]) {
                search(next);
            } else {
                count++;
            }
        }
        if (count >= 2) {
            isTree = false;
        }
    }
}
