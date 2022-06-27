package boj;

import java.util.*;
import java.io.*;

class Boj2186 {
    static char[][] arr;
    static int[][][] dp;
    static int N, M, K;
    static char[] word;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        word = br.readLine().toCharArray();
        dp = new int[N][M][word.length];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (word[0] == arr[i][j]) {
                    count += DFS(i, j, 0);
                }
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static int DFS(int r, int c, int depth) {

        // 이곳은 이미 탐색했던 곳이고 총 몇개의 경로가 앞으로 나올지 저장되어있으므로
        // 다시 탐방하는것은 무의미 따라서 저장된 값 반환 --> Memorization
        if (dp[r][c][depth] != -1) {
            return dp[r][c][depth];
        }
        // 지금 탐방하는곳이 처음 탐방하는곳일때는 단어의 길이와 depth가 동일하면 탐색완료 이므로 1반환
        if (depth == word.length - 1) {
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < 4; j++) {
                int nextR = r + row[j] * i;
                int nextC = c + col[j] * i;

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                // 다음 탐색할 구간이 단어의 다음 알파벳과 동일하다면
                if (arr[nextR][nextC] == word[depth + 1]) {
                    // 이쪽방향으로 가는 가짓수를 모두 더함
                    count += DFS(nextR, nextC, depth + 1);
                }
            }
        }
        // 현재 dp 정보에 위에서 탐방한 가짓수를 후에 이용하기 위해 저장
        dp[r][c][depth] = count;
        return count;
    }
}