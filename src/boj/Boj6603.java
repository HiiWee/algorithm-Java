package boj;

import java.io.*;
import java.util.*;

/**
 * 정해진 숫자로 조합을 생성하고, 사전순으로 정렬하면 됨
 * 이미 입력된 숫자가 사전순 조합이므로 따로 정렬할 필요가 없다.
 */
class Boj6603 {
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numLength = Integer.parseInt(st.nextToken());
            if (numLength == 0) {
                break;
            }
            numbers = new int[numLength];
            for (int i = 0; i < numLength; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            combination(new boolean[numLength], 0, numLength, 6);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void combination(boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            append(visited);
            return;
        }

        // 따로 정렬하지 않아도 사전순으로 조합이 생성된다.
        for (int i = depth; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }

    public static void append(boolean[] visited) {
        for (int i = 0; i < numbers.length; i++) {
            if (visited[i]) {
                sb.append(numbers[i]).append(" ");
            }
        }
        sb.append("\n");
    }
}