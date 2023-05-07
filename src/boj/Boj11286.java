package boj;

/*
    우선순위 큐 조건에서 절대값을 기준으로 비교해야 함
*/

import java.io.*;
import java.util.*;

class Boj11286 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();

        // 절대값이 가장 작은것이 우선순위를 가짐
        // 동일한 절대값이라면 음수인경우가 우선순위를 가진다.
        PriorityQueue<Integer> pq = new PriorityQueue<>((num1, num2) -> {
            int absNum1 = Math.abs(num1);
            int absNum2 = Math.abs(num2);

            if (absNum1 != absNum2) {
                return absNum1 - absNum2;
            }
            return num1 - num2;
        });

        while (n-- > 0) {
            int number = Integer.parseInt(br.readLine());
            if (number == 0) {
                if (pq.isEmpty()) {
                    result.append(0).append("\n");
                } else {
                    result.append(pq.poll()).append("\n");
                }
            } else {
                pq.offer(number);
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}
