package boj;

/*
    특정 파티에서 다른 파티로의 최소 경로를 모두 구해야 하므로 다익스트라 알고리즘은 적합하지 않다.
    (다익스트라는 특정 시작점에서 다른 정점으로의 최소 경로를 구하는데 적합하다.)
    만약 다익스트라를 이용하면 M개의 경우에서 한번씩 다익스트라 알고리즘으로 그 값을 구해야 한다. -> 비효율

    따라서 플로이도 워셜 방식으로 특정 정점에서 다른 정점으로 갈 수 있는 최소값을 모두 구하고
    M개의 경우를 각각 받아서 다음 파티가 열리는 시간보다 적게 걸리면 해당 파티장으로 이동하는 방식으로 풀 수 있다.
*/

import java.util.*;
import java.io.*;

class Boj11265 {
    static final String POSSIBLE = "Enjoy other party";
    static final String IMPOSSIBLE = "Stay here";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] party = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        floyd(party, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            if (time < party[start][end]) {
                sb.append(IMPOSSIBLE).append("\n");
            } else {
                sb.append(POSSIBLE).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void floyd(int[][] map, int n) {
        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    map[from][to] = Math.min(map[from][to], map[from][middle] + map[middle][to]);
                }
            }
        }
    }
}