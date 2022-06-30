package programmers;

import java.util.*;

public class MakeBiggestNumber {
    /*
        보장되어야 하는 수를 남겨두고 앞에서 차례대로 큰 수를 구한다.
        4177252841 , k = 4 이라면 6개를 뽑아야함
        따라서 최소 52841 앞에서 하나가 뽑혀야함 --> 이때 인덱스2의 7이 뽑히고 index + 1로 탐색위치 갱신
        이후 5개를 뽑아야하므로 2841앞에서 하나가 뽑혀얌 --> 반복작업
    */
    // 뽑아야 하는 수 관점으로 문제 풀이
    static class Solution_1 {
        public String solution(String number, int k) {
            StringBuilder sb = new StringBuilder();
            int target = number.length() - k;
            int start = 0;
            for (int i = 0; i < number.length() - k; i++) {
                int max = 0;
                for (int j = start; j <= i + k; j++) {
                    int num = number.charAt(j) - '0';
                    if (num > max) {
                        start = j + 1;
                        max = num;
                    }
                }
                sb.append(max);
            }
            return sb.toString();
        }
    }

    // 삭제하는 수 관점으로 문제 풀이
    static class Solution_2 {
        public String solution(String number, int k) {
            Stack<Character> stack = new Stack<>();
            int target = number.length() - k;
            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);

                // 일단 스택에 담은 수들중 큰 수가 존재하면 빼준다, 하지만 k값을 감소시키면서 빼주는값을 카운트한다.
                // 로직이 기똥차다 아주그냥..!
                while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                    stack.pop();
                }

                stack.push(c);
            }
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < target; i++) {
                sb.append(stack.get(i));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution_1 solution_1 = new Solution_1();
        Solution_2 solution_2 = new Solution_2();

        String[] numbers = {"1924", "1231234", "4177252841"};
        int[] k = {2, 3, 4};
        String[] results = {"94", "3234", "775841"};

        System.out.println("solution_1 = " + solution_1);
        for (int i = 0; i < numbers.length; i++) {
            String answer = solution_1.solution(numbers[i], k[i]);
            if (answer.equals(results[i])) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
        System.out.println();

        System.out.println("solution_2 = " + solution_2);
        for (int i = 0; i < numbers.length; i++) {
            String answer = solution_2.solution(numbers[i], k[i]);
            if (answer.equals(results[i])) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
