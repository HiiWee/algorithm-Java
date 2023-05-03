package boj;

/*
    우선순위 큐를 이용해 큰 정수 순서대로 출력하도록 한다.
*/

import java.io.*;
import java.util.*;

class Boj11279 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder result = new StringBuilder();

        while (n-- > 0) {
            int number = Integer.parseInt(br.readLine());
            if (number == 0 && !pq.isEmpty()) {
                result.append(pq.poll()).append("\n");
            } else if (number == 0 && pq.isEmpty()) {
                result.append(0).append("\n");
            } else {
                pq.offer(number);
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}