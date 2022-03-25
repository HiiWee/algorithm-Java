package programmers;

import java.util.*;

public class Printer {
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        // 우선순위를 담은 큐
        Queue<Integer> priorityQue = new LinkedList<>();
        // 최초 인쇄 요청 한 순서들의 index 큐
        Queue<Integer> que = new LinkedList<>();
        // 중복제거한 우선순위 내림차순 정렬 리스트
        List<Integer> list = new ArrayList<>();

        // for문을 돌면서 각 큐와 list에 값을 초기화
        for (int i = 0; i < priorities.length; i++) {
            priorityQue.add(priorities[i]);
            que.add(i);
            if (!list.contains(priorities[i])) {
                list.add(priorities[i]);
            }
        }
        // 리스트 내림차순 정렬
        Collections.sort(list, Collections.reverseOrder());

        int i = 0;
        while (i < list.size()) {
            // 요청한 순서대로 파일의 우선순위, 인덱스 추출
            int temp1 = priorityQue.poll();
            int temp2 = que.poll();
            // 남아있는 프린트 중에서 가장 높은 우선순위 추출
            int priority = list.get(i);

            // 현재 파일의 우선순위가 프린트 목록에 있는 가장 큰 우선순위 보다 작다면 맨 뒤로 보냄
            if (temp1 < priority) {
                priorityQue.add(temp1);
                que.add(temp2);
                // 현재 파일의 우선순위가 프린트 목록에서 제일 큰 우선순위와 동일하다면 프린트 함
            } else if (temp1 == priority) {
                // 다른 파일에도 동일한 우선순위를 가진다면 프린트한 수만 증가함
                if (priorityQue.contains(temp1)) {
                    answer++;
                    // 다른 파일에 동일 우선순위를 가진 파일이 없다면 프린트한 수 증가, 다음으로 큰 우선순위로 이동
                } else {
                    answer++;
                    i++;
                }
                // 현재 프린트한 파일이 location과 동일하다면 반복문 탈출
                if (temp2 == location)
                    break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] priorities = {{2, 1, 3, 2}, {1, 1, 9, 1, 1, 1}, {5, 4, 3, 2, 1}, {1, 1, 1, 1, 1}, {1, 2, 3, 4, 5}};
        int[] location = {2, 0, 3, 3, 1};
        int[] results = {1, 5, 4, 4, 4};

        for (int i = 0; i < location.length; i++) {
            int result = solution(priorities[i], location[i]);
            if (results[i] == result) {
                System.out.printf("pass [%d]\n", result);
            }
        }
    }
}
