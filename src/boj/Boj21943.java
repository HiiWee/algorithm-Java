package boj;

/*
    모든 숫자에 대한 순열을 구하고
    곱하기 위치만 조합을 이용해 정해준다.
*/

import java.io.*;
import java.util.*;

class Boj21943 {

    private static int n, p, q;
    private static int[] numbers;
    private static int[] expressions;
    private static boolean[] visited;
    private static int maxValue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        numbers = new int[n];
        visited = new boolean[n];
        expressions = new int[2 * n - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        permutations(0);

        bw.write(maxValue + "");
        bw.flush();
        bw.close();
    }

    // 숫자의 모든 순서 구하기
    public static void permutations(int index) {
        if (index == n) {
            combinations(0, n - 1, q);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                expressions[index * 2] = numbers[i];
                permutations(index + 1);
                visited[i] = false;
            }
        }
    }

    // 숫자 순서에서 곱하기의 위치를 정해주기
    public static void combinations(int index, int n, int r) {
        if (r == 0) {
            for (int expression : expressions) {
                System.out.print(expression + " ");
            }
            System.out.println();
            updateMaxValue();
            return;
        }

        for (int i = index; i < n; i++) {
            expressions[i * 2 + 1] = '*';
            combinations(i + 1, n, r - 1);
            expressions[i * 2 + 1] = 0;
        }
    }

    public static void updateMaxValue() {
        int[] sumNumbers = new int[q + 1];
        int currentSum = 0;
        int index = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            if (expressions[i] == '*') {
                sumNumbers[index++] = currentSum;
                currentSum = 0;
            } else {
                currentSum += expressions[i];
            }
        }
        sumNumbers[index] = currentSum;

        int totalSum = 1;
        for (int number : sumNumbers) {
            totalSum *= number;
        }
        maxValue = Math.max(maxValue, totalSum);
    }
}