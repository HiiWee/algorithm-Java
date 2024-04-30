package boj;

import java.io.*;
import java.util.*;

class Boj16198 {

    static int n;
    static int[] numbers;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        visited[n - 1] = true;

        search(visited, 0, 0);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    public static void search(boolean[] visited, int index, int sum) {
        if (index == n - 2) {
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int leftValue = findLeftValue(i, visited);
                int rightValue = findRightValue(i, visited);
                visited[i] = true;
                search(visited, index + 1, sum + leftValue * rightValue);
                visited[i] = false;
            }
        }
    }

    public static int findLeftValue(int index, boolean[] visited) {
        for (int i = index - 1; i >= 0; i--) {
            if (!visited[i]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    public static int findRightValue(int index, boolean[] visited) {
        for (int i = index + 1; i < n; i++) {
            if (!visited[i]) {
                return numbers[i];
            }
        }
        return numbers[n - 1];
    }
}

