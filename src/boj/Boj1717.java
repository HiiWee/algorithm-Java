package boj;

import java.io.*;
import java.util.*;

class Boj1717 {

    static int n, m;
    static int[] set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        set = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            set[i] = i;
        }

        StringBuilder answer =  new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op == 0) {
                union(a, b);
            } else {
                if (isSameSet(a, b)) {
                    answer.append("YES\n");
                } else {
                    answer.append("NO\n");
                }
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static boolean isSameSet(int a, int b) {
        return find(a) == find(b);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        // 큰 번호가 루트가 되게 합치기
        if (a > b) {
            set[b] = a;
        } else {
            set[a] = b;
        }
    }

    public static int find(int a) {
        if (set[a] == a) {
            return a;
        }
        set[a] = find(set[a]);
        return set[a];
    }
}