package boj;

import java.io.*;
import java.util.*;

class Boj17398 {

    static int n, m, q;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        Arrays.fill(parents, -1);
        String[] edge = new String[m];
        for (int i = 0; i < m; i++) {
            edge[i] = br.readLine();
        }

        boolean[] excludes = new boolean[m];
        for (int i = 0; i < q; i++) {
            excludes[Integer.parseInt(br.readLine()) - 1] = true;
        }

        for (int i = 0; i < m; i++) {
            if (excludes[i]) {
                continue;
            }

            st = new StringTokenizer(edge[i]);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        long answer = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (!excludes[i]) {
                continue;
            }
            st = new StringTokenizer(edge[i]);
            int a = find(Integer.parseInt(st.nextToken()));
            int b = find(Integer.parseInt(st.nextToken()));

            if (a == b) {
                continue;
            }

            answer += ((long) -parents[a]) * -parents[b];

            union(a, b);
        }

        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }
        if (a > b) {
            parents[b] += parents[a];
            parents[a] = b;
        } else {
            parents[a] += parents[b];
            parents[b] = a;
        }
    }

    public static int find(int a) {
        if (parents[a] < 0) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }
}
