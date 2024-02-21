package programmers;

import java.util.*;

public class 같은_숫자는_싫어 {
    public Integer[] solution(int[] arr) {
        List<Integer> answer = new ArrayList<>();

        int preNumber = -1;
        for (int number : arr) {
            if (number != preNumber) {
                answer.add(number);
                preNumber = number;
            }
        }
        return answer.toArray(new Integer[0]);
    }
}
