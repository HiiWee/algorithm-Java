package boj;

import java.io.*;
import java.util.*;

class Boj1316 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        while (n-- > 0) {
            char[] line = br.readLine().toCharArray();
            boolean[] visited = new boolean[26];

            boolean isPossible = true;
            int i = 0;
            while (i < line.length) {
                if (visited[line[i] - 'a']) {
                    isPossible = false;
                    break;
                }
                visited[line[i] - 'a'] = true;
                char c = line[i];
                while (i < line.length && c == line[i]) {
                    i++;
                }
            }
            if (isPossible) {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}