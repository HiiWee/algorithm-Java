package boj;


import java.io.*;
import java.util.*;

class Boj10423 {

    static int n, m, k;
    static int[] parent;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int power = Integer.parseInt(st.nextToken());
            parent[power] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{u, v, x});
        }

        int count = 0;
        int cost = 0;
        while (count < n - k) {
            int[] edge = pq.poll();

            int a = find(edge[0]);
            int b = find(edge[1]);

            if (a == b) {
                continue;
            }
            if (a > b) {
                parent[a] = b;
            } else {
                parent[b] = a;
            }
            count++;
            cost += edge[2];
        }

        bw.write(Integer.toString(cost));
        bw.flush();
        bw.close();
    }

    public static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}

