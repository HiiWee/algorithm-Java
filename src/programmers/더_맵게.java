package programmers;

import java.util.*;

class 더_맵게 {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int value : scoville) {
            pq.offer(value);
        }

        int answer = 0;
        while (pq.size() > 1) {
            if (pq.peek() >= K) {
                break;
            }
            answer++;
            int value1 = pq.poll();
            int value2 = pq.poll();
            int newValue = value1 + value2 * 2;
            pq.offer(newValue);
        }
        if (pq.peek() < K) {
            return -1;
        }
        return answer;
    }
}