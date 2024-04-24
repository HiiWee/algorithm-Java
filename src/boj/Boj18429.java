package boj;

import java.io.*;
import java.util.*;

class Boj18429 {

    static int n, k;
    static int[] numbers;
    static int possibleCount;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        numbers = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        permuataions(0, 500);

        bw.write(Integer.toString(possibleCount));
        bw.flush();
        bw.close();
    }

    public static void permuataions(int count, int sum) {
        if (count == n) {
            if (sum >= 500) {
                possibleCount++;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && sum - k + numbers[i] >= 500) {
                visited[i] = true;
                permuataions(count + 1, sum - k + numbers[i]);
                visited[i] = false;
            }
        }
    }
}
