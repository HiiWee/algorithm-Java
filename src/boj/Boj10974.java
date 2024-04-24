package boj;

import java.io.*;
import java.util.*;

class Boj10974 {

    static int n;
    static int[] out;
    static boolean[] visited;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        out = new int[n];
        visited = new boolean[n];

        permutations(0);

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void permutations(int count) {
        if (count == n) {
            for (int number : out) {
                answer.append(number).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[count] = i + 1;
                permutations(count + 1);
                visited[i] = false;
            }
        }
    }
}
