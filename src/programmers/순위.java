package programmers;

import java.util.*;

class 순위 {

    static final int INF = 1000000;

    int[][] graph;
    int n;
    int[][] results;

    public int solution(int n, int[][] results) {
        this.n = n;
        this.results = results;
        init();

        for (int m = 1; m <= n; m++) {
            for (int f = 1; f <= n; f++) {
                for (int t = 1; t <= n; t++) {
                    if (graph[f][m] == 1 && graph[m][t] == 1) {
                        graph[f][t] = 1;
                        graph[t][f] = -1;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            boolean isPossible = true;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == INF) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                answer++;
            }
        }

        return answer;
    }

    public void init() {
        graph = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        for (int[] result : results) {
            graph[result[0]][result[1]] = 1;
            graph[result[1]][result[0]] = -1;
        }
    }
}