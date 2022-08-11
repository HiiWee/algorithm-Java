package algorithm.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
sample input(첫 번째 숫자는 노드의 개수, 두 번째 숫자는 간선의 개수 이다).
5
8
0 1 5
0 4 1
0 2 7
0 3 2
1 2 3
1 3 6
2 3 10
3 4 4
 */
public class FloydWarshall {
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) throws NumberFormatException, IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // 플로이드 초기 거리 테이블 초기화
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 자기 자신으로 가는 길은 최소 비용이 0이다.
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                // 자기 자신으로 가는 경우를 제외하고는 매우 큰 값(N개의 노드를 모두 거쳐서 가더라도 더 큰 값).
                dist[i][j] = 100_000_000;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 가는 경로가 하나가 아닐 수 있다. 따라서 그 중 최소 비용을 저장해두면 된다.
            dist[a][b] = Math.min(dist[a][b], cost);
            dist[b][a] = Math.min(dist[b][a], cost);
        }

        // 플로이드 워셜 알고리즘
        // 노드를 1개부터 N개까지 거쳐가는 경우를 모두 고려한다.
        for (int middle = 0; middle < N; middle++) {
            // 노드 from에서 to로 가는 경우.
            for (int from = 0; from < N; from++) {
                for (int to = 0; to < N; to++) {
                    // middle 노드를 거쳐가는 비용이 기존 비용보다 더 작은 경우 갱신
                    // 또는 연결이 안되어있던 경우(INF) 연결 비용 갱신.
                    dist[from][to] = Math.min(dist[from][to], dist[from][middle] + dist[middle][to]);
                }
            }
        }

        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 연결이 안되어 있는 경우
                if (dist[i][j] == 100_000_000) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}