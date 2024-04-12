package boj;

import java.io.*;
import java.util.*;

class Boj16562 {

    static int n, m, k;
    static int[] cost;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cost = new int[n];
        parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;
            unionWithRebaseMoney(v, w);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                sum += cost[i];
            }
        }

        if (sum > k) {
            bw.write("Oh no");
        } else {
            bw.write(Integer.toString(sum));
        }
        bw.flush();
        bw.close();
    }

    public static boolean unionWithRebaseMoney(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }
        if (a > b) {
            parents[a] = b;
            cost[b] = Math.min(cost[a], cost[b]);
        } else {
            parents[b] = a;
            cost[a] = Math.min(cost[a], cost[b]);
        }

        return true;
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }
}
