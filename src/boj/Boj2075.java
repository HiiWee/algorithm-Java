package boj;

/*
    모든 수를 우선순위큐에 넣고 N번째 뽑히는 값을 출력한다.
*/

import java.io.*;
import java.util.*;

class Boj2075 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }
        while (n-- > 1) {
            pq.poll();
        }

        bw.write(pq.poll() + "");
        bw.flush();
        bw.close();
    }
}