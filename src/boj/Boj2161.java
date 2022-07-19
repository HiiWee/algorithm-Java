package boj;

import java.util.*;
import java.io.*;

class Boj2161 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            que.add(i);
        }
        StringBuilder sb = new StringBuilder();
        while (que.size() > 1) {
            sb.append(que.poll()).append(" ");
            que.add(que.poll());
        }
        sb.append(que.poll());
        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
    }
}