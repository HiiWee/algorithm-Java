package boj;


import java.io.*;
import java.util.*;

class Boj1240 {

    static int n, m;
    static List<int[]>[] trees;
    static boolean[] visited;
    static int distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        trees = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            trees[a].add(new int[]{b, c});
            trees[b].add(new int[]{a, c});
        }

        StringBuilder answer = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            visited = new boolean[n + 1];
            search(start, end, 0);
            answer.append(distance).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int current, int end, int sum) {
        visited[current] = true;

        if (current == end) {
            distance = sum;
            return;
        }

        for (int[] next : trees[current]) {
            if (!visited[next[0]]) {
                search(next[0], end, sum + next[1]);
            }
        }
    }
}
