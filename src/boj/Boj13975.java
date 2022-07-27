package boj;

/*
    1. 우선순위큐에 모든 값을 담고 2개씩 빼서 더하고 다시 큐에 삽입한다.
       이떄 더한 값들은 누적값으로 따로 저장해준다.
    2. 최종적으로 큐에 데이터가 하나만 남게되면 종료
*/

import java.io.*;
import java.util.*;

class Boj13975 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> que = new PriorityQueue<>();
            for (int i = 0; i < k; i++) {
                que.add(Long.parseLong(st.nextToken()));
            }
            long total = 0;
            while (que.size() > 1) {
                long num1 = que.poll();
                long num2 = que.poll();
                total += num1 + num2;
                que.add(num1 + num2);
            }
            sb.append(total).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}