package boj;

import java.io.*;
import java.util.*;

class Boj5430 {

    private static final String ERROR = "error";
    private static final char REVERSE = 'R';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            boolean isSuccess = true;
            int currentPosition = 0; // 0은 앞, 1은 뒤
            String p = br.readLine();
            int numberLength = Integer.parseInt(br.readLine());
            Deque<Integer> numbers = parseNumbers(numberLength, br.readLine());
            for (char command : p.toCharArray()) {
                if (command == REVERSE) {
                    currentPosition = (currentPosition + 1) % 2;
                } else if (!pollNumber(currentPosition, numbers)) {
                    isSuccess = false;
                    break;
                }
            }
            if (!isSuccess) {
                answer.append(ERROR).append("\n");
            } else {
                answer.append(createResult(currentPosition, numbers)).append("\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static String createResult(int position, Deque<Integer> numbers) {
        if (numbers.size() == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        result.append("[");
        if (position == 0) {
            while (!numbers.isEmpty()) {
                result.append(numbers.poll()).append(",");
            }
        } else {
            while (!numbers.isEmpty()) {
                result.append(numbers.pollLast()).append(",");
            }
        }

        result.deleteCharAt(result.length() - 1);
        result.append("]");
        return result.toString();
    }

    public static boolean pollNumber(int position, Deque<Integer> numbers) {
        if (numbers.isEmpty()) {
            return false;
        }
        if (position == 0) {
            numbers.poll();
        } else {
            numbers.pollLast();
        }

        return true;
    }

    public static Deque<Integer> parseNumbers(int length, String input) {
        Deque<Integer> numbers = new ArrayDeque<>();
        if (length == 0) {
            return numbers;
        }
        StringTokenizer st = new StringTokenizer(input.substring(1, input.length() - 1), ",");
        while (length-- > 0) {
            numbers.offer(Integer.parseInt(st.nextToken()));
        }
        return numbers;
    }
}