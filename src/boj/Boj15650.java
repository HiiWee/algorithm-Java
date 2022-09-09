/*
    단순 조합
*/

import java.io.*;
import java.util.*;

class Boj15650 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        combination(new boolean[n], n, m, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void combination(boolean[] visited, int n, int r, int depth) {
        if (r == 0) {
            append(visited);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, i + 1);
            visited[i] = false;
        }
    }

    public static void append(boolean[] visited) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(i + 1).append(" ");
            }
        }
        sb.append("\n");
    }
}