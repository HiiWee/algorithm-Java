package boj;

import java.io.*;
import java.util.*;

class Boj18115 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] answer = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            deq.offer(i);
        }
        for (int i = 0; i < n; i++) {
            int currentNumber = n - i;
            int skill = Integer.parseInt(st.nextToken());
            if (skill == 1) {
                answer[deq.poll()] = currentNumber;
            } else if (skill == 2) {
                int top = deq.poll();
                answer[deq.poll()] = currentNumber;
                deq.offerFirst(top);
            } else {
                answer[deq.pollLast()] = currentNumber;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int number : answer) {
            result.append(number).append(" ");
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}