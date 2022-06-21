package programmers;

import java.util.Arrays;

public class TheBiggestNumber {
    public static String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, (o1, o2) -> {
            return (o2 + o1).compareTo(o1 + o2);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        if (sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] numbersList = {{6, 10, 2}, {3, 30, 34, 5, 9}, {4, 48, 4845, 8}};
        String[] results = {"6210", "9534330", "84848454"};
        for (int i = 0; i < results.length; i++) {
            String solution = solution(numbersList[i]);
            if (results[i].equals(solution)) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }

}