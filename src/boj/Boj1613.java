package boj;
/*
    기본적으로 그래프를 INF로 초기화 하고 자기 자신을 가리키는 부분은 0으로 초기화한다.
    전후 관계를 파악하면 되므로 방향성이 있는 그래프로 저장하고,
    단지 전후 관계 파악이 목적이므로 입력받은 가중치는 1로 모두 초기화 해준다.

    이후 플로이드 워셜을 돈다.
    전후관계 파악을 위한 번호를 받아서 만약 i j일 경우
    map[j][i] == INF이고, map[i][j] != INF이면 -1
    map[i][j] == INF이고, map[j][i] != INF이면 1
    둘 다 INF의 값을 갖는다면 연관이 없으므로 0을 출력한다.
*/
import java.util.*;
import java.io.*;

class Boj1613 {
    static final int INF = 10000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            map[s][e] = 1;
        }

        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (map[from][to] > map[from][middle] + map[middle][to]) {
                        map[from][to] = map[from][middle] + map[middle][to];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (map[start][end] != INF) {
                sb.append("-1\n");
            } else if (map[end][start] != INF) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
