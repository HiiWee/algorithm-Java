package boj;

import java.io.*;
import java.util.*;

class Boj17352 {

    static int n;
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < n - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) {
                bw.write(Integer.toString(i) + " ");
            }
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
        if (a > b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }

    public static int find(int a) {
        if (a == parents[a]) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }
}
