package boj;

/*
    허프만 코드: 우선순위 큐를 이용해 간단하게 풀 수 있다.
*/

import java.util.*;
import java.io.*;

class Boj1715 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> que = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            que.add(Integer.parseInt(br.readLine()));
        }

        int total = 0;
        while (que.size() > 1) {
            int num1 = que.poll();
            int num2 = que.poll();
            total += num1 + num2;
            que.add(num1 + num2);
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}