package boj;

/*
    임의의 수를 받아서 조합
*/

import java.io.*;
import java.util.*;

class Main {
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        permutation(new int[m], new boolean[n], n, m, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, boolean[] visited, int n, int r, int depth) {
        if (depth == r) {
            append(out);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                permutation(out, visited, n, r, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void append(int[] out) {
        for (int num : out) {
            sb.append(num).append(" ");
        }
        sb.append("\n");
    }
}