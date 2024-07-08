package boj;

import java.io.*;
import java.util.*;

class Boj20364 {

    static int n, q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        boolean[] trees = new boolean[n + 1];

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int number = Integer.parseInt(br.readLine());
            int findNumber = 0;
            for (int j = number; j >= 1; j /= 2) {
                if (trees[j]) {
                    findNumber = j;
                }
            }
            if (findNumber != 0) {
                answer.append(findNumber).append("\n");
            } else {
                trees[number] = true;
                answer.append("0\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
