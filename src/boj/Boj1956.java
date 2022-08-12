package boj;

/*
    결국 왕복을통해 처음 점으로 돌아오는 사이클을 찾아야 한다
    모든 사이클 중에서 가장 최소값인 사이클을 찾기위해선 플로이드 워셜 알고리즘이 적합하다.
    기존 플로이드 워셜 알고리즘은 자기자신으로 가는것은 0으로 처리했지만 여기서는 각 사이클의 최소를 구해야 한다
    따라서, 시작과 도착이 동일한 사이클 중 최소의 경로를 구하기 위해서는
    시작과 출발이 같은 대각선 요소들을 INF값으로 두고 알고리즘을 시작한다.
    이후 플로이드가 종료되면 각 대각선의 값들중 최소의값이 최소 경로를 갖는 운동경로가된다.
*/

import java.util.*;
import java.io.*;

class Boj1956 {
    static final int INF = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] map = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(map[i], INF);
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[start][end] = cost;
        }

        for (int middle = 1; middle <= v; middle++) {
            for (int from = 1; from <= v; from++) {
                for (int to = 1; to <= v; to++) {
                    map[from][to] = Math.min(map[from][to], map[from][middle] + map[middle][to]);
                }
            }
        }
        int min = INF;
        for (int i = 1; i <= v; i++) {
            min = Math.min(map[i][i], min);
        }
        if (min == INF) {
            bw.write("-1\n");
        } else {
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }
}