package boj;

import java.io.*;
import java.util.*;

class Boj1368 {

    static int n;
    static int[] parent;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int digCost = Integer.parseInt(br.readLine());
            parent[i] = i;
            pq.offer(new int[]{0, i, digCost});
        }

        for (int i = 1; i <= n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (j <= i) {
                    continue;
                }
                pq.offer(new int[]{i, j, cost});
            }
        }

        int count = 0;
        int minCost = 0;
        while (count < n) {
            int[] edge = pq.poll();
            if (union(edge[0], edge[1])) {
                minCost += edge[2];
                count++;
            }
        }

        bw.write(Integer.toString(minCost));
        bw.flush();
        bw.close();
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }

        return true;
    }

    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }
}

