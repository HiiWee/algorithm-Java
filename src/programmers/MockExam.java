package programmers;

import java.util.*;

class MockExam {
    public static int[] solution(int[] answers) {
        int[][] guesses = {{1, 2, 3, 4, 5}, {2 ,1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1 ,2, 2, 4, 4, 5, 5}};
        int[] counts = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (guesses[0][i % guesses[0].length] == answers[i]) counts[0]++;
            if (guesses[1][i % guesses[1].length] == answers[i]) counts[1]++;
            if (guesses[2][i % guesses[2].length] == answers[i]) counts[2]++;
        }

        List<Integer> list = new ArrayList<>();
        int max = Math.max(Math.max(counts[0], counts[1]), counts[2]);
        if (counts[0] == max) list.add(1);
        if (counts[1] == max) list.add(2);
        if (counts[2] == max) list.add(3);

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i).intValue();
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] answers = {{1, 2, 3, 4, 5}, {1, 3, 2, 4, 2}};
        for (int i = 0; i < answers.length; i++) {
            System.out.println(Arrays.toString(solution(answers[i])));
        }
    }
}