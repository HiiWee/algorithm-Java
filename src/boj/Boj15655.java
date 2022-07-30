package boj;

/*
    조합론을 이용해 풀 수 있다.
*/

import java.io.*;
import java.util.*;

class Main {
    static int[] arr;
    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        combination(new boolean[n], 0, m);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void combination(boolean[] visited, int depth, int r) {
        if (r == 0) {
            append(visited);
            return;
        }
        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, i + 1, r - 1);
            visited[i] = false;
        }
    }

    public static void append(boolean[] visited) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(arr[i]).append(" ");
            }
        }
        sb.append("\n");
    }
}