package boj;

import java.io.*;
import java.util.*;

class Boj21279 {

    public static final int MAX = 100_000;
    static class Mineral {
        int x;
        int v;

        public Mineral(int x, int v) {
            this.x = x;
            this.v = v;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Mineral>[] minerals = new ArrayList[MAX + 1];
        int[] xValueSums = new int[MAX + 1];
        int[] xCostCounts = new int[MAX + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (minerals[y] == null) {
                minerals[y] = new ArrayList<>();
            }
            minerals[y].add(new Mineral(x, v));
        }

        int y = 0;
        int mineralCount = 0;
        long sumValue = 0;
        long maxValue = 0;

        for (int x = MAX; x >= 0; x--) {

            while (mineralCount < c && y <= MAX) {
                if (minerals[y] == null) {
                    y++;
                    continue;
                }

                for (Mineral mineral : minerals[y]) {
                    if (mineral.x <= x) {
                        sumValue += mineral.v;
                        mineralCount++;

                        xValueSums[mineral.x] += mineral.v;
                        xCostCounts[mineral.x]++;
                    }
                }

                if (mineralCount <= c) {
                    maxValue = Math.max(maxValue, sumValue);
                }
                y++;
            }

            if (mineralCount <= c) {
                maxValue = Math.max(maxValue, sumValue);
            }
            if (xValueSums[x] > 0) {
                sumValue -= xValueSums[x];
                mineralCount -= xCostCounts[x];
            }
        }

        bw.write(maxValue + "");
        bw.flush();
        bw.close();
    }
}