package boj;

import java.io.*;
import java.util.*;

class Boj14888 {

    static int n;
    static int[] numbers;
    // 덧셈, 뺄셈, 곱셈, 나눗셈
    static int[] operators = new int[4];
    static int[] opSequence;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        opSequence = new int[n - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        permutations(0);

        bw.write(max + "\n" + min);
        bw.flush();
        bw.close();
    }

    public static void permutations(int index) {
        if (index == n - 1) {
            int result = calculate();
            min = Math.min(result, min);
            max = Math.max(result, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                opSequence[index] = i;
                permutations(index + 1);
                operators[i]++;
            }
        }
    }

    public static int calculate() {
        int num = numbers[0];

        for (int i = 1; i < n; i++) {
            int operand = numbers[i];
            int operator = opSequence[i - 1];
            if (operator == 0) {
                num += operand;
            } else if (operator == 1) {
                num -= operand;
            } else if (operator == 2) {
                num *= operand;
            } else {
                if (num < 0) {
                    num = -(Math.abs(num) / operand);
                } else {
                    num /= operand;
                }
            }
        }

        return num;
    }
}