package boj;

/*
    최소 한 명이상으로 구성된 팀이어야 하며, 두 팀의 숫자는 같지 않아도 된다.
    즉 하나의 팀을 기준으로 뽑고 뽑히지 않은 사람들을 나머지 팀으로 두면 된다. --> 조합 이용
    여기서 하나의 팀에 모든 경우를 뽑는 조합은 제외시켜야 하므로 다음과 같은 경우를 구해야 한다.
    N명일 경우, NC1, NC2, NC3 ... NCN-1 까지의 조합을 구하고 두 개의 팀으로 나누어 전력의 차의 최소를 구한다.
*/

import java.io.*;
import java.util.*;

class Boj15661 {
    static int n;
    static int[][] map;
    static int totalStatus;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        totalStatus = getTotalStatus();
        combination(new boolean[n], 0);

        bw.write(min + "\n");
        bw.flush();
        bw.close();

    }

    public static void combination(boolean[] visited, int depth) {
        if (depth == n) {
            // 0 ~ N사이의 선수들만 뽑았는지 -> 한 팀에 최소의 팀원인 함명 이상 보장
            updateStatus(visited);
            return;
        }

        visited[depth] = true;
        combination(visited, depth + 1);
        visited[depth] = false;
        combination(visited, depth + 1);
    }

    public static void updateStatus(boolean[] visited) {
        int sumStart = 0;
        int sumLink = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i] && visited[j]) {
                    sumStart += map[i][j];
                } else if (!visited[i] && !visited[j]) {
                    sumLink += map[i][j];
                }
            }
        }
        if (sumStart != 0 && sumStart != totalStatus) {
            min = Math.min(min, Math.abs(sumStart - sumLink));
        }
    }

    public static int getTotalStatus() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }
}