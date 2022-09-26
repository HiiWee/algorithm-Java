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


/*
* 백트래킹을 이용한 풀이
* 외판원 순회에서 특징은 순회경로(사이클)을 만들 수 있다면
* 시작점을 어떤점에서 시작하든 결국 정답 경로를 지나치게 되므로 시작점이 어떤점인지는 상관이 없다.
* 따라서 0번점을 시작점으로 고정하고 순회를 시작한다.
*
* 백트래킹으로 순회를 돌다가 방문한 횟수가 n이되고 현재 인덱스의 값이 start와 같다면 순회의 마지막인 마지막 점 -> 시작 점의 경우를 진행한다.
* 이후 최소값을 계속 갱신한다.
* */
class Boj10971_2 {
    static int n;
    static int[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[n];
        visited[0] = true;
        backTracking(visited, 0, 0, 1, 0);

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    public static void backTracking(boolean[] visited, int start, int cur, int depth, int sum) {
        if (depth == n && start == cur) {
            min = Math.min(sum, min);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (map[cur][i] > 0) {
                if (i == start && depth == n) {
                    backTracking(visited, start, i, depth, sum + map[cur][i]);
                } else if (!visited[i]) {
                    visited[i] = true;
                    backTracking(visited, start, i, depth + 1, sum + map[cur][i]);
                    visited[i] = false;
                }
            }

        }
    }
}