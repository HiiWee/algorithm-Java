package programmers;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class FailureRate {
    public static int[] solution(int N, int[] stages) {
        Map<Integer, Float> map = new HashMap<>();
        // 현재 스테이지보다 크거나 동일 값 : 현재 스테이지 도전함
        // 현재 스테이지와 동일한 값 : 현재 스테이지 도전했지만 실패
        for (int i = 1; i <= N; i++) {
            int failCount = 0;
            int tryCount = 0;
            for (int j = 0; j < stages.length; j++) {
                if (stages[j] > i) {
                    tryCount++;
                } else if (stages[j] == i) {
                    failCount++;
                    tryCount++;
                }
            }
            // 0.0 / 0.0 발생시 NaN값 처리
            float rate;
            if (failCount == 0 && tryCount == 0) {
                rate = 0f;
            } else {
                rate = (float) failCount / (float) tryCount;
            }
            map.put(i, rate);
        }
        // Value기준 정렬
        List<Map.Entry<Integer, Float>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).getKey();
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] N = {5, 4};
        int[][] stages = {{2, 1, 2, 6, 2, 4, 3, 3}, {4, 4, 4, 4, 4}};
        int[][] results = {{3, 4, 2, 1, 5}, {4, 1, 2, 3}};

        for (int i = 0; i < N.length; i++) {
            int[] answer = solution(N[i], stages[i]);
            int count = 0;
            for (int j = 0; j < answer.length; j++) {
                if (answer[j] == results[i][j]) {
                    count++;
                }
            }
            if (count == results[i].length) {
                System.out.println("Test " + (i + 1) + " is Passed");
            }
        }
    }
}