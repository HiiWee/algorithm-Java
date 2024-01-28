package boj;

import java.io.*;
import java.util.*;

class Boj6443 {

    static char[] lines;
    static char[] out;
    static StringBuilder results = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            lines = br.readLine().toCharArray();
            Arrays.sort(lines);
            out = new char[lines.length];
            permutations(new boolean[lines.length], 0);
        }

        bw.write(results.toString());
        bw.flush();
        bw.close();
    }

    public static void permutations(boolean[] visited, int index) {
        if (index == lines.length) {
            results.append(out).append("\n");
            return;
        }

        char pre = ' ';
        for (int i = 0; i < lines.length; i++) {
            if (!visited[i] && pre != lines[i]) {
                visited[i] = true;
                pre = lines[i];
                out[index] = lines[i];
                permutations(visited, index + 1);
                visited[i] = false;
            }
        }
    }
}
