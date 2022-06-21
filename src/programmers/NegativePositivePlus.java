package programmers;

public class NegativePositivePlus {
    public static int solution(int[] absolutes, boolean[] signs) {
        int total = 0;

        for (int i = 0; i < signs.length; i++) {
            if (signs[i]) {
                total += absolutes[i];
            } else {
                total += (-absolutes[i]);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] absolutes = {{4, 7, 12}, {1, 2, 3}};
        boolean[][] signs = {{true, false, true}, {false, false, true}};
        int[] results = {9, 0};

        for (int i = 0; i < results.length; i++) {
            int solution = solution(absolutes[i], signs[i]);
            if (results[i] == solution) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
