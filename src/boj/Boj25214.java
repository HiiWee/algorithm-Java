package boj;

import java.io.*;
import java.util.*;

class Boj25214 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        int answer = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int number = Integer.parseInt(st.nextToken());
            min = Math.min(min, number);
            answer = Math.max(answer, number - min);
            result.append(answer).append(" ");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}