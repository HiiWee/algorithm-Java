package boj;

import java.io.*;
import java.util.*;

class Boj14676 {

    static int n, m, k;
    static List<Integer>[] graphs;
    static Set<Integer>[] phases;
    static int[] inDegrees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graphs = new ArrayList[n];
        phases = new HashSet[n];
        inDegrees = new int[n];

        for (int i = 0; i < n; i++) {
            graphs[i] = new ArrayList<>();
            phases[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graphs[a].add(b);
            inDegrees[b]++;
        }

        int[] buildCount = new int[n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken()) - 1;

            if (command == 1 && inDegrees[number] != 0
                    || command == 2 && buildCount[number] == 0) {
                System.out.println("Lier!");
                return;
            }

            if (command == 1) {
                buildCount[number]++;

                for (int next : graphs[number]) {
                    if (!phases[next].contains(number)) {
                        inDegrees[next]--;
                        phases[next].add(number);
                    }
                }
            } else {
                buildCount[number]--;
                if (buildCount[number] == 0) {
                    for (int next : graphs[number]) {
                        phases[next].remove(number);
                        inDegrees[next]++;
                    }
                }
            }
        }

        bw.write("King-God-Emperor");
        bw.flush();
        bw.close();
    }
}
