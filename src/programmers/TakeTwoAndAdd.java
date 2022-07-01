package programmers;

import java.util.*;

public class TakeTwoAndAdd {

    static class Solution_BruteForce {
        Set<Integer> set = new HashSet<>();

        public Integer[] solution(int[] numbers) {
            Integer[] answer;
            for (int i = 0; i < numbers.length; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    set.add(numbers[i] + numbers[j]);
                }
            }
            answer = set.toArray(new Integer[set.size()]);
            Arrays.sort(answer);
            return answer;
        }
    }

    static class Solution_Combination {
        List<Integer> list = new ArrayList<>();
        int[] arr;

        public Integer[] solution(int[] numbers) {
            arr = numbers;
            combination(new boolean[arr.length], 0, arr.length, 2);
            Collections.sort(list);


            return list.toArray(new Integer[list.size()]);
        }

        public void combination(boolean[] visited, int depth, int n, int r) {
            if (r == 0) {
                addToList(visited);
                return;
            }

            for (int i = depth; i < n; i++) {
                visited[i] = true;
                combination(visited, i + 1, n, r - 1);
                visited[i] = false;
            }

        }

        public void addToList(boolean[] visited) {
            int total = 0;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    total += arr[i];
                }
            }
            if (!list.contains(total)) {
                list.add(total);
            }
        }
    }

    public static void main(String[] args) {
        Solution_BruteForce solution_bruteForce;
        Solution_Combination solution_combination;

        int[][] numbers = {{2, 1, 3, 4, 1}, {5, 0, 2, 7}};
        int[][] results = {{2, 3, 4, 5, 6, 7}, {2, 5, 7, 9, 12}};

        System.out.println("solution_bruteForce");
        boolean flag = true;
        for (int i = 0; i < numbers.length; i++) {
            solution_bruteForce = new Solution_BruteForce();
            Integer[] answer = solution_bruteForce.solution(numbers[i]);
            for (int j = 0; j < answer.length; j++) {
                if (answer[j] != results[i][j]) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
            flag = true;
        }
        System.out.println();

        System.out.println("solution_combination");
        flag = true;
        for (int i = 0; i < numbers.length; i++) {
            solution_combination = new Solution_Combination();
            Integer[] answer = solution_combination.solution(numbers[i]);
            for (int j = 0; j < answer.length; j++) {
                if (answer[j] != results[i][j]) {
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
            flag = true;
        }
    }
}
