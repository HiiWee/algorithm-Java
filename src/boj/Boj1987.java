package boj;


/**
 * 백트래킹 + DFS
 *
 *     1. A ~ Z사이의 대문자만 입력으로 주어짐
 *     2. 따라서 알파벳의 개수만큼 visited를 생성
 *     3. DFS를 돌면서 상하좌우 탐방하며 가능하면 탐방(매개변수로 카운트 넘겨줌)
 */
/*

*/

import java.util.*;
import java.io.*;

class Main {
    static int R, C;
    static char[][] map;
    static boolean[] visited;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};
    static int moveCount = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        visited[map[0][0] - 'A'] = true;
        DFS2(0, 0, 1);

        bw.write(moveCount + "\n");
        bw.flush();
        bw.close();
    }

    /**
     *  DFS를 돌며 백트래킹, 먼저 방문 처리를 하고 자식을 방문하는 과정 이렇게 되면 선 판별을 위한 초기 if문으로 return을 한다.
     *  이후 상하좌우를 모두 돌고나면 현재 방문한 곳을 다른경로의 탐색을 위해 false를 한다 --> 자기 자신 판단 후 자식 방문(여기서도 자식이 스스로 자신을 판단)
     * @param r: 행
     * @param c: 열
     * @param count: 서로다른 방문횟수
     */
    public static void DFS1(int r, int c, int count) {
        if (visited[map[r][c] - 'A']) {
            return;
        }
        if (count > moveCount) {
            moveCount = count;
        }
        visited[map[r][c] - 'A'] = true;
        for (int i = 0; i < 4; i++) {
            int nextR = r + row[i];
            int nextC = c + col[i];

            if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) {
                continue;
            }
            if (!visited[(map[nextR][nextC] - 'A')]) {
                DFS1(nextR, nextC, count + 1);
            }
        }
        visited[map[r][c] - 'A'] = false;
    }

    /**
     * DFS를 돌며 백트래킹, 일단 방문을 먼저하고, 최대값을 갱신한다.
     * 이후 자식을 아직 방문하지 않았다면 방문처리를 하고 재귀를 돈다.
     * 재귀가 종료되면 다시 방금 방문한 자식을 미방문 처리하여 다른 경로를 탐색할때 이용할 수 있게한다.
     * 흔히 조합, 순열에서 사용되는 방식과 모습이 비슷하다.
     *
     * 하지만 주의할점은 맨 처음 방문해야 하는곳은 메소드 안에서 방문처리되지 않으므로 호출전에 방문처리를 해줘야한다.
     *
     */
    public static void DFS2(int r, int c, int count) {
        moveCount = Math.max(count, moveCount);

        for (int i = 0; i < 4; i++) {
            int nextR = r + row[i];
            int nextC = c + col[i];

            if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) {
                continue;
            }

            if (!visited[map[nextR][nextC] - 'A']) {
                visited[map[nextR][nextC] - 'A'] = true;
                DFS2(nextR, nextC, count + 1);
                visited[map[nextR][nextC] - 'A'] = false;
            }
        }
    }
}
