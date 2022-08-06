package boj;
/*
    플로이드 와샬 알고리즘을 이용해 풀이한다.
    모든 정점을 middle로 두고 from -> to로 직접 가는 값과, from -> middle -> to로
    가는 값들 중 가장 최소인 값을 계속해서 갱신해 최소 경로를 구할 수 있다.
    (다만 음의 사이클이 존재하면 안된다.)
*/

import java.util.*;
import java.io.*;

class Boj11404 {
    static final int MAX = 10000001;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(matrix[i], MAX);
            matrix[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[s][e] = Math.min(matrix[s][e], c);
        }

        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    matrix[from][to] = Math.min(matrix[from][to], matrix[from][middle] + matrix[middle][to]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] == MAX) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(matrix[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}