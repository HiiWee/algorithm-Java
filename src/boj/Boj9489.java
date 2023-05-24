package boj;

import java.io.*;
import java.util.*;

class Boj9489 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            int[] parents = new int[n + 1];
            int[] nodes = new int[n + 1];
            int targetIndex = -1;
            int parentIndex = -1;
            nodes[0] = -1;
            parents[0] = -1;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                nodes[i] = Integer.parseInt(st.nextToken());
                if (nodes[i] == k) {
                    targetIndex = i;
                }
                if (nodes[i] != nodes[i - 1] + 1) {
                    parentIndex++;
                }
                parents[i] = parentIndex;
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (parents[i] != parents[targetIndex] && parents[parents[i]] == parents[parents[targetIndex]]) {
                    count++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }
}