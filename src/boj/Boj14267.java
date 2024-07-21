package boj;

import java.io.*;
import java.util.*;

class Boj14267 {

    static int n, m;
    static List<Integer>[] graph;
    static int[] values;
    static int[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        values = new int[n + 1];
        answer = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int i = 2; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].add(i);
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            values[a] += value;
        }

        dfs(1, 0);

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            result.append(answer[i]).append(" ");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void dfs(int node, int sum) {
        sum += values[node];
        answer[node] = sum;

        for (int next : graph[node]) {
            dfs(next, sum);
        }
    }
}
