package programmers;

import java.util.*;

class N으로_표현 {

    public int solution(int N, int number) {
        Set<Integer>[] dp = new HashSet[8 + 1];

        for (int i = 0; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }

        int answer = -1;
        for (int k = 1; k <= 8; k++) {
            dp[k].add(iterNumber(N, k));

            for (int i = 1; i < k; i++) {
                for (int number1 : dp[i]) {
                    for (int number2 : dp[k - i]) {
                        dp[k].add(number1 + number2);
                        if (number1 - number2 > 0) {
                            dp[k].add(number1 - number2);
                        }
                        if (number1 / number2 != 0) {
                            dp[k].add(number1 / number2);
                        }
                        dp[k].add(number1 * number2);
                    }
                }
            }
            if (dp[k].contains(number)) {
                answer = k;
                break;
            }
        }
        return answer;
    }

    public int iterNumber(int value, int count) {
        int number = 0;
        for (int i = 0; i < count; i++) {
            number = number * 10 + value;
        }
        return number;
    }
}