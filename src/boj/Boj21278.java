package boj;

/*
    먼저 그래프는 양방향 그래프이며 연결그래프이다.
    플로이드 워셜 알고리즘을 통해 각 정점사이의 최단 경로를 구한다.
    모든 도시들 중에서 2개의 집을 골라 치킨집을 구해야 하므로
    조합을 이용해 2개의 집을 선택할 수 있는 경우를 모두 구한다.

    이후 각 조합을 하나씩 뽑아서 다른 도시에서 해당 조합에 포함된 집으로 가는 거리의 합을 구하고
    최소를 갱신힌다. 최소값이 갱신될때마다 해당 조합의 번호와 거리를 같이 갱신해준다.
*/

import java.io.*;
import java.util.*;

class Boj21278 {
    final static int INF = 1000000;
    static int min = Integer.MAX_VALUE;
    static int n, m;
    static int[][] map;
    static List<String> combiList = new ArrayList<>();
    static int c1, c2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s][e] = 1;
            map[e][s] = 1;
        }
        floyd();
        combination(new boolean[n + 1], n, 2, 1);

        for (String line : combiList) {
            st = new StringTokenizer(line, " ");
            int chicken1 = Integer.parseInt(st.nextToken());
            int chicken2 = Integer.parseInt(st.nextToken());
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                if (i == chicken1 || i == chicken2) {
                    continue;
                }
                // 왕복 거리 이므로
                sum += Math.min(map[i][chicken1], map[i][chicken2]) * 2;
            }
            if (min > sum) {
                c1 = chicken1;
                c2 = chicken2;
                min = sum;
            }
        }

        bw.write(c1 + " " + c2 + " " + min + "\n");
        bw.flush();
        bw.close();
    }

    public static void floyd() {
        for (int middle = 1; middle <= n; middle ++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (map[from][to] > map[from][middle] + map[middle][to]) {
                        map[from][to] = map[from][middle] + map[middle][to];
                    }
                }
            }
        }
    }

    public static void combination(boolean[] visited, int n, int r, int depth) {
        if (r == 0) {
            append(visited);
            return;
        }

        for (int i = depth; i <= n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, i + 1);
            visited[i] = false;
        }
    }

    public static void append(boolean[] visited) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                sb.append(i).append(" ");
            }
        }
        combiList.add(sb.toString());
    }
}