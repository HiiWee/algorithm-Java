package boj;

/*
    from > middle > to라면 from > to이므로 from과 to는 관계가 있다.
    해당 물건과의 비교결과를 알 수 없는 물건이라면 위와 같은 대소관계 자체가 주어지지 않은 경우를 말한다.
    따라서 모든 관계(from -> middle -> to라면 from -> to 성립)의 파악을 위해 플로이드 워셜 알고리즘을 이용한다.

    우선 각 시작 -> 끝 노드를 받아서 인접행렬로 저장하는데 이때 가중치는 전부 1로 저장한다.
    1로 저장하는 이유는 1 > 2, 2 > 3일때 1 > 3이 성립되어야 하는데 이때, 단지 관계만 파악하면 되므로
    임의의 수인 1로 초기화 하고, 후에 INF인 값만 카운트하면 된다.

    결과를 출력할때 from -> to 성립되거나 to -> from 될 경우 대소관계가 있다고 판단한다.
    만약 둘 중 하나만 확인하게 되면 1 -> 2로 가는 경로가 존재하면 1번 물건에서는 1 > 2의 대소관계가 있다 판단하겠지만
    2번 물건에서는 1로 갈 수 없기 때문에 INF값일 것이고 따라서 경로가 존재하지 않는다고 판단되기 때문이다.
*/
import java.util.*;
import java.io.*;

class Boj10159 {
    public static final int INF = 100000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], INF);
            map[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <=n; j++) {
                if (map[i][j] == INF && map[j][i] == INF) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}