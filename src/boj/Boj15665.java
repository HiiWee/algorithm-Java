package boj;

/*
    입력받은 숫자에서 뽑았던 수를 또 뽑을 수 있는 순열 만들기, 단 똑같은 숫자가 주어질 경우
    중복되는 순열이 생성될 수 있으므로 중복을 제거해야한다.
*/

import java.io.*;
import java.util.*;

class Boj15665 {
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

        permutation(new int[m], n, m, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, int n, int r, int depth) {
        if (depth == r) {
            append(out);
            return;
        }

        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == arr[i]) {
                continue;
            }
            prev = arr[i];
            out[depth] = arr[i];
            permutation(out, n, r, depth + 1);
        }
    }

    public static void append(int[] out) {
        for (int i = 0; i < out.length; i++) {
            sb.append(out[i]).append(" ");
        }
        sb.append("\n");
    }
}