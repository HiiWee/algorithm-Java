package boj;

import java.io.*;
import java.util.*;

class Boj3584 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] parents = new int[n + 1];
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                parents[child] = parent;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[n + 1];

            int currentParent = parents[a];
            visited[a] = true;
            while (currentParent != 0) {
                visited[currentParent] = true;
                currentParent = parents[currentParent];
            }

            while (!visited[b]) {
                visited[b] = true;
                b = parents[b];
            }
            result.append(b).append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}