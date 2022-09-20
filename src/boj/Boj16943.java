package boj;

/*
    먼저 A로 만들 수 있는 모든 순열을 구하고,
    각 순열을 하나씩 비교해 B보다 작으면 최대값을 갱신한다.
    이 때 0으로 시작하는 수는 선택되면 안된다.
    아무런 수도 없다면 -1을 출력
*/

import java.io.*;
import java.util.*;

class Boj16943 {
    static String A;
    static int B;
    static int[] arr;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        B = Integer.parseInt(st.nextToken());

        makeArr(A);

        permutation(new int[A.length()], new boolean[A.length()], A.length(), A.length(), 0);

        if (max == Integer.MIN_VALUE) {
            max = -1;
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void makeArr(String num) {
        arr = new int[num.length()];

        for (int i = 0; i < num.length(); i++) {
            arr[i] = num.charAt(i) - '0';
        }
    }

    public static void permutation(int[] out, boolean[] visited, int n, int r, int depth) {
        if (depth == r) {
            int num = getNum(out);
            if (num < B) {
                max = Math.max(max, num);
            }
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

    public static int getNum(int[] out) {
        int n = 0;
        for (int i = 0; i < out.length; i++) {
            if (i == 0 && out[i] == 0) {
                return Integer.MIN_VALUE;
            }
            n = 10 * n;
            n += out[i];
        }
        return n;

    }
}