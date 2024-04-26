package boj;

import java.util.*;
import java.io.*;

class Boj19699 {

    static int n, m;
    static boolean[] prime = new boolean[9001];
    static int[] cows;
    static List<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cows = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        createPrime();
        combinations(0, 0, 0);

        answers.sort(Comparator.naturalOrder());

        if (answers.isEmpty()) {
            bw.write("-1");
        } else {
            StringBuilder result = new StringBuilder();
            for (int answer : answers) {
                result.append(answer).append(" ");
            }
            bw.write(result.toString());
        }
        bw.flush();
        bw.close();
    }

    public static void combinations(int index, int count, int sum) {
        if (count == m) {
            if (!prime[sum]) {
                prime[sum] = true;
                answers.add(sum);
            }
            return;
        }

        for (int i = index; i < n; i++) {
            combinations(i + 1, count + 1, sum + cows[i]);
        }
    }

    public static void createPrime() {
        prime[0] = true;
        prime[1] = true;
        for (int i = 2; i <= 9000; i++) {
            if (prime[i]) {
                continue;
            }
            for (int j = i * 2; j <= 9000; j += i) {
                prime[j] = true;
            }
        }
    }
}
