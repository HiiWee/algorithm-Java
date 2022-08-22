package boj;

/*
    먼저 치킨은 최대 3개를 튀길 수 있으므로 전체 치킨에서 3가지를 뽑는 조합을 구한다.
    모든 조합을 하나싹 꺼내고, 각 사람들을 돌면서 조합에서 나온 3가지 치킨 중 가장 높은 점수의 치킨을 고른다.
    모든 사람들에게서 3가지 치킨 중 최대 점수 치킨을 구하고 전부 더해준다.
    이렇게 했을때 3가지중 1개의 치킨만 선택될 수 있으나 최대 3마리 이므로 1마리 이상, 3마리 이하의 치킨만 선택되어도 된다.

    하나의 조합에 대한 점수가 구해지면 최대값을 갱신한다.

    모든 조합에 대한 최대 점수의 갱신이 종료되면 결과를 출력한다.
*/

import java.io.*;
import java.util.*;

class Boj16439 {
    static List<String> list = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combination(new boolean[m + 1], m, 3, 1);

        for (String select : list) {
            int sumScore = 0;
            st = new StringTokenizer(select);
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            int three = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= n; i++) {
                sumScore += Math.max(arr[i][three], Math.max(arr[i][one], arr[i][two]));
            }
            max = Math.max(max, sumScore);
        }

        bw.write(max + "\n");
        bw.flush();
        bw.close();
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
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) {
                sb.append(i).append(" ");
            }
        }
        list.add(sb.toString());
    }
}