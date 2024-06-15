package boj;

import java.io.*;
import java.util.*;

class Boj2026 {

    static int k, n, f;
    static boolean[][] friends;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        friends = new boolean[n + 1][n + 1];

        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a][b] = true;
            friends[b][a] = true;
        }

        search(1, 0, new int[k]);

        bw.write("-1");
        bw.flush();
        bw.close();
    }

    public static void search(int person, int index, int[] out) {
        if (index == k) {
            StringBuilder answer = new StringBuilder();
            for (int o : out) {
                answer.append(o).append("\n");
            }
            System.out.println(answer);
            System.exit(0);
        }

        for (int i = person; i <= n; i++) {
            if (isPossible(i, out, index)) {
                out[index] = i;
                search(i + 1, index + 1, out);
            }
        }
    }

    public static boolean isPossible(int number, int[] out, int currentIndex) {
        for (int i = 0; i < currentIndex; i++) {
            if (!friends[out[i]][number]) {
                return false;
            }
        }
        return true;
    }
}
