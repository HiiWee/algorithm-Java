package algorithm.shortestpath;

import java.util.Arrays;

public class BellmanFord {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int num = 5;
        int[][] adj = new int[][] {
                {0, -4, 5, 2, 3},
                {INF, 0, INF, -1, INF},
                {INF, INF, 0, -7, INF},
                {INF, INF, INF, 0, 6},
                {INF, INF, INF, -4, 0},
        };
        int src = 0;
        int dst = 1;

        solve(num, adj, src, dst);
    }

    public static void solve(int num, int[][] adj, int src, int dst) {
        int[] dists = new int[num];
        Arrays.fill(dists, INF);
        dists[src] = 0;

        for(int v=0; v < num; ++v) {
            for(int w=0; w < num; ++w) {
                if(adj[v][w] != INF)
                    dists[w] = Math.min(dists[w], dists[v] + adj[v][w]);
            }
        }

        for(int i=0; i< num; ++i)
            System.out.println(dists[i]);
    }
}