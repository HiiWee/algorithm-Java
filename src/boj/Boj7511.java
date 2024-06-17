package boj;

import java.io.*;
import java.util.*;

class Boj7511 {

    static final String FORMAT = "Scenario %d:\n";
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        int count = 0;
        StringBuilder answer = new StringBuilder();
        while (count < t) {
            count++;
            answer.append(String.format(FORMAT, count));
            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());
            parents = new int[n];

            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }

            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            int m = Integer.parseInt(br.readLine());

            while (m-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (find(a) == find(b)) {
                    answer.append("1\n");
                } else {
                    answer.append("0\n");
                }
            }
            answer.append("\n");
        }

        bw.write(answer.toString());
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
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }
}
