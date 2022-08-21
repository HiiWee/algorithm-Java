package boj;

/*
    각 케이스별로 3 x 3을 입력받으면 이를 한줄짜리 비트로 변환한다. (H == 1, T == 0)
    이후 BFS를 돌며 0 또는 전부 1로 채울 수 있는 경우를 찾아야 한다.

    한 행의 모든 동전, 한 열의 모든 동전, 대각선의 동전을 뒤집을 수 있는 경우는 총 8개이고 다음과 같이 정의된다.
    111    000    000    100    010    001    100    001
    000    111    000    100    010    001    010    010
    000    000    111    100    010    001    001    100
    위의 기준들을 가지고 기존 동전에 XOR 연산을 해주어야 동전이 계속 뒤집힌다.

    모든 연산의 결과는 0 ~ 511(111111111(2))까지 이므로 해당 수에 해당되는 512개의 visited 배열을 만들어놓고
    BFS를 돌며 중복을 제거해 간다.

    visited 배열을 이용해 중복방문을 처리하는 방법은
        1. int 타입으로 시작해 현재 방문한 지점이 몇번째인지 더해가는 방식이 있고
        2. que.size()만큼 반복하며 초기 큐의 사이즈만큼 전부 돌면 1번의 연산이 종료된것으로 간주하고 카운트하고
           visited 배열은 boolean 타입으로 두어 중복 방문 체크를 하는 방식이 존재한다.
*/

import java.util.*;
import java.io.*;

class Boj9079 {
    static int[] visited;
    static int[] flipArr = new int[]{448, 56, 7, 292, 146, 73, 273, 84};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringBuilder line = symbolToBinary(br);
            int num = Integer.parseInt(line.toString(), 2);

            if (BFS(num)) {
                sb.append("-1\n");
            } else {
                sb.append(Math.min(visited[0], visited[511])).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static StringBuilder symbolToBinary(BufferedReader br) throws IOException {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                String token = st.nextToken();
                if (token.equals("H")) {
                    line.append("1");
                } else {
                    line.append("0");
                }
            }
        }
        return line;
    }

    private static boolean BFS(int num) {
        visited = new int[512];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<Integer> que = new LinkedList<>();
        que.add(num);

        visited[num] = 0;
        boolean flag = true;
        while (!que.isEmpty()) {
            int cur = que.poll();
            if (cur == 0 || cur == 511) {
                flag = false;
                break;
            }
            for (int flipNum : flipArr) {
                int next = cur ^ flipNum;
                if (visited[next] == Integer.MAX_VALUE) {
                    que.add(next);
                    visited[next] = visited[cur] + 1;
                }
            }
        }
        return flag;
    }
}