package boj;

/*
    순열을 통해 해당 문자열 길이로 나올 수 있는 모든 순열을 구하고
    구한 순열에 대해서 행운의 문자열인지 판단한다.
*/

import java.io.*;
import java.util.*;

class Boj1342 {
    static String line;
    static int count;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        line = br.readLine();
        int length = line.length();

        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            boolean[] visited = new boolean[length];
            visited[i] = true;
            sb.append(line.charAt(i));
            permutation(sb, visited, length, length, 1);

        }

        int[] alpha = new int[26];
        for (int i = 0; i < length; i++) {
            alpha[line.charAt(i) - 'a']++;
        }

        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] >= 2) {
                count /= factorial(alpha[i]);
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void permutation(StringBuilder sb, boolean[] visited, int n, int r, int depth) {
        if (depth == r) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (sb.charAt(sb.length() - 1) != line.charAt(i)) {
                    visited[i] = true;
                    sb.append(line.charAt(i));
                    permutation(sb, visited, n, r, depth + 1);
                    sb.deleteCharAt(sb.length() - 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static int factorial(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

}