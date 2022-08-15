package boj;
/*
    플로이드 워셜의 원리는 i -> j로 가는 경로가 있고 i -> k, k -> j로 가는 경로중 후자가 더 최단경로라면
    해당 경로로 초기화해준다. 따라서 이의 역을 이용하면 i -> j와 i -> k -> j가 동일하면 i -> j의 경로를
    지워준다.

    또한 middle과 from, to에서 나온 pair는 서로 같으면 안된다 예를들어 middle == to일경우
    from -> to = from -> middle + middle -> to == from -> to = from -> to + to -> to(0)
    결국 from -> to = from -> to + 0인데도 다리를 지우게 되므로 오류가 발생한다.

    또한 불가능한 경우에는 -1을 출력해야 하는데 이는 초기에 최소경로가 입력되지 않고
    기존 플로이드의 조건인 from->to < from -> middle + middle -> to가 성립되면 초기 조건자체가
    모순이 존재하는 것이므로 -1을 출력한다.
*/

import java.io.*;
import java.util.*;

class Boj1507 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        boolean[][] check = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                if (middle == from) {
                    continue;
                }
                for (int to = 1; to <=n; to++) {
                    if (middle == to || from == to) {
                        continue;
                    }

                    if (map[from][to] > map[from][middle] + map[middle][to]) {
                        bw.write("-1\n");
                        bw.flush();
                        bw.close();
                        return;
                    }
                    if (map[from][to] == map[from][middle] + map[middle][to]) {
                        check[from][to] = true;
                    }
                }
            }
        }
        int total = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!check[i][j]) {
                    total += map[i][j];
                    check[j][i] = true;
                }
            }
        }

        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}