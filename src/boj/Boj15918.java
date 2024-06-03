package boj;

import java.io.*;
import java.util.*;

class Boj15918 {

    static int n, x, y;
    static int[] out;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        out = new int[2 * n];
        visited = new boolean[n + 1];

        visited[y - x - 1] = true;
        out[x - 1] = y - x - 1;
        out[y - 1] = y - x - 1;

        search(0, out);

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] out) {
        if (index == 2 * n) {
            count++;
            return;
        }

        if (out[index] != 0) {
            search(index + 1, out);
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i] && isPossible(i, index, out)) {
                out[index] = i;
                out[index + i + 1] = i;
                visited[i] = true;
                search(index + 1, out);
                out[index] = 0;
                out[index + i + 1] = 0;
                visited[i] = false;
            }
        }
    }

    public static boolean isPossible(int number, int index, int[] out) {
        int nextIndex = index + number + 1;
        if (nextIndex < 2 * n && out[nextIndex] == 0) {
            return true;
        }
        return false;
    }
}
