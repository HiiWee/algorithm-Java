package boj;

import java.io.*;
import java.util.*;

class Boj4195 {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int f = Integer.parseInt(br.readLine());
            parents = new int[f * 2 + 1];
            int number = 1;
            Map<String, Integer> names = new HashMap<>();

            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();
                if (!names.containsKey(a)) {
                    parents[number] = -1;
                    names.put(a, number++);
                }
                if (!names.containsKey(b)) {
                    parents[number] = -1;
                    names.put(b, number++);
                }

                union(names.get(a), names.get(b));
                answer.append(-parents[find(names.get(a))]).append("\n");
            }
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
            parents[b] += parents[a];
            parents[a] = b;
        } else {
            parents[a] += parents[b];
            parents[b] = a;
        }
    }

    public static int find(int a) {
        if (parents[a] < 0) {
            return a;
        }

        return parents[a] = find(parents[a]);
    }
}
