package boj;

import java.io.*;
import java.util.*;

class Boj1976 {

    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                boolean canTravel = Integer.parseInt(st.nextToken()) == 1;
                if (canTravel) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> distinct = new HashSet<>();
        for (int i = 0; i < m; i++) {
            distinct.add(find(Integer.parseInt(st.nextToken())));
        }

        if (distinct.size() > 1) {
            bw.write("NO");
        } else {
            bw.write("YES");
        }
        bw.flush();
        bw.close();
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        parents[a] = find(parents[a]);
        return parents[a];
    }
}