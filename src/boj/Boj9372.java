package boj;

import java.io.*;
import java.util.*;

class Boj9372 {

    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            parent = new int[n];
            int m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            int count = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                if (union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)) {
                    count++;
                }
            }

            answer.append(count).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        br.close();
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
