package boj;

/*
    큐를 이용해서 풀어보자
*/

import java.io.*;
import java.util.*;

class Boj2164 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            que.add(i);
        }

        while (que.size() > 1) {
            que.poll();
            que.add(que.poll());
        }

        bw.write(que.poll() + "\n");
        bw.flush();
        bw.close();
    }
}