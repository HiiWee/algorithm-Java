package programmers;

import java.util.*;

class 디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        // 요청시간이 짧은 순서대로 가져옴
        Arrays.sort(jobs, Comparator.comparingInt(j -> j[0]));
        // 처리시간이 짧은 잡을 먼저 가져오도록 함
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int answer = 0;
        int jobIndex = 0;
        int count = 0;
        int end = 0;
        // 전체 잡을 다 돌때까지
        while (count < jobs.length) {

            // 잡의 인덱스를 넘지 않는 선에서, 현재 잡이 끝나는 시점보다 요청시점이 더 작거나 같다면 일단 큐에 다 추가함
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= end) {
                que.offer(jobs[jobIndex++]);
            }
            // 큐가 비어있다면, (0, 2), (3, 2) 처럼 두 job의 사이가 띄워져있으므로 현재 잡의 end에 맞춰줌
            if (que.isEmpty()) {
                end = jobs[jobIndex][0];
            } else {
                // 잡을 하나씩 꺼내서 실행시키는데 이때 처리시간이 짧은 잡을 먼저 가져와서 end를 증가시키고, 결과값도 더합니다(끝나 시간 - 요청시간)
                int[] job = que.poll();
                count++;
                end += job[1];
                answer += end - job[0];
            }
        }
        return answer / jobs.length;
    }
}