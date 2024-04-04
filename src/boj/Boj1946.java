package boj;

import java.io.*;
import java.util.*;

class Boj1946 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] rank = new int[n + 1];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                rank[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }

            int count = 1;
            int min = rank[1];
            for (int i = 2; i <= n; i++) {
                if (min > rank[i]) {
                    min = rank[i];
                    count++;
                }
            }
            answer.append(count).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
