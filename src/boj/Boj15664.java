package boj;

/*
    임의의 수 N개에서 동일한 수가 존재할 수 있으므로
    모든 조합을 뽑게 되면 동일한 조합이 발생할 수 있다.
    따라서 재귀를 돌때 이전에 선택한 값을 저장하는 변수를 이용해 중복 조합을 생성하는것을 방지하자
*/

import java.io.*;
import java.util.*;

class Boj15664 {
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
            if (prev != arr[i]) {
                prev = arr[i];
                out[depth] = arr[i];
                combination(out, n, r, depth + 1, i + 1);
            }
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}