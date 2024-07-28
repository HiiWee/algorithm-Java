package boj;

import java.io.*;
import java.util.*;

class Boj1595 {

    static List<String> inputs = new ArrayList<>();
    static List<int[]>[] tree;
    static int farNode;
    static int maxDist;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = "test";

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            inputs.add(line);
        }

        tree = new ArrayList[inputs.size() + 2];

        for (int i = 0; i <= inputs.size() + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        for (String input : inputs) {
            StringTokenizer st = new StringTokenizer(input);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new int[]{b, c});
            tree[b].add(new int[]{a, c});
        }

        visited = new boolean[inputs.size() + 2];
        dfs(1, 0);
        Arrays.fill(visited, false);
        dfs(farNode, 0);

        bw.write(Integer.toString(maxDist));
        bw.flush();
        bw.close();
    }

    public static void dfs(int node, int sum) {
        if (sum > maxDist) {
            maxDist = sum;
            farNode = node;
        }

        visited[node] = true;

        for (int[] next : tree[node]) {
            if (!visited[next[0]]) {
                dfs(next[0], sum + next[1]);
            }
        }
    }
}

