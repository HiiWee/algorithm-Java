package boj;

import java.util.*;
import java.io.*;

class Boj14391 {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        DFS(0, 0);


        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(int row, int col) {
        // 모든 행을 전부 탐색함 즉 전체 탐색 완료
        if (row >= n) {
            updateMaxSum();
            return;
        }
        if (col >= m) {
            DFS(row + 1, 0);
            return;
        }

        // 2차원 map을 하나의 배열로 봤을때 모든 조합을 구하는 경우를 구해야
        // 가로, 세로 종이 조각을 만드는 모든 경우를 만들 수 있다.
        visited[row][col] = true;
        DFS(row, col + 1);

        visited[row][col] = false;
        DFS(row, col + 1);
    }

    public static void updateMaxSum() {
        int sum = 0;

        // 가로 더하기
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    temp *= 10;    // 기존 숫자 자리올림하고
                    temp += map[i][j]; // 새로운 숫자 추가
                } else {
                    sum += temp;    // 세로인 false 만나면 현재까지 구한 값 더해주기
                    temp = 0;    // 더했으니 다시 temp 초기화
                }
            }
            // 마지막 행에 혹시나 구하다가 남은 temp 값 존재하면 더하기
            sum += temp;
        }

        // 세로 더하기
        for (int i = 0; i < m; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j][i]) {
                    temp *= 10;
                    temp += map[j][i];
                } else {
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        max = Math.max(max, sum);
    }
}