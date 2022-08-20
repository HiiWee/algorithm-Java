package boj;

/*
    서로 다른 정수를 구해야 하므로 각 숫자의 순서에따라 다른 정수값이 나타난다.
    따라서 순열구하여 중복을 제거하고 값을 찾는다.
*/

import java.util.*;
import java.io.*;

class Boj5568 {
    static int[] arr;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        permutation(new int[n], new boolean[n], 0, n, k);
        bw.write(set.size() + "\n");
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            append(out);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                permutation(out, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static void append(int[] out) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]);
        }
        set.add(sb.toString());
    }
}