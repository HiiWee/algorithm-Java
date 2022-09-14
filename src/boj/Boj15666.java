package boj;

/*
    N개의 주어지는 수 중에서 중복되는 수가 존재하므로 이를 이용해 중복조합을 만들면
    겹치는 수가 존재하므로 각 조합이 서로 동일해지지 않도록 중복을 제거해야 한다.
*/

import java.io.*;
import java.util.*;

class Boj15666 {
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

        combination(new int[m], n, m, 0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void combination(int[] out, int n, int r, int depth, int start) {
        if (depth == r) {
            append(out);
            return;
        }

        int prev = -1;
        for (int i = start; i < n; i++) {
            if (prev == arr[i]) {
                continue;
            }
            prev = arr[i];
            out[depth] = arr[i];
            // 자신을 뽑아도 또 뽑을 수 있으므로 i + 1이 아니라 i를 이용해 재귀를 돌림
            combination(out, n, r, depth + 1, i);
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}
