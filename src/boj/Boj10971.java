package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Boj10971 {
    static boolean[] visited;
    static int N;
    static int minTotalValue = Integer.MAX_VALUE;
    static int[][] matrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutation(new int[N], 0, N);
        bw.write(minTotalValue + "\n");
        bw.flush();
        bw.close();
    }

    public static void permutation(int[] out, int depth, int r) {
        if (depth == r) {
            calcMinValue(out);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                // 인덱스만 저장해도 됨
                out[depth] = i;
                permutation(out, depth + 1, N);
                visited[i] = false;
            }
        }
    }

    public static void calcMinValue(int[] out) {
        int totalValue = 0;
        for (int i = 0; i < N; i++) {
            int value;
            if (i == N - 1) {
                value = matrix[out[i]][out[0]];
            } else {
                value = matrix[out[i]][out[i + 1]];
            }
            if (value == 0) {
                return;
            }
            // 갈 수 없다는 의미이므로 순회할 수 없는 경로다.
            totalValue += value;
        }
        minTotalValue = Math.min(minTotalValue, totalValue);
    }
}
