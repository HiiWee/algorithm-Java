package boj;

/*
    경사사로로 올라가거나, 내려가는 두 가지의 경우
    두 가지 경우 모두 이전의 숫자와 현재 숫자가 다를때 검사할 수 있음
    내려갈때: curIndex, ... curIndex + L - 1가 존재하고 블록들이 curIndex - 1과 1높이 차이가 나면
    올라갈때: curIndex - L, ... curIndex - 1이 존재하고 이전 블록들이 curIndex와 1높이 차이가 나면
*/
import java.io.*;
import java.util.*;

class Boj14890 {

    static int n, l, count;
    static int[][] map1, map2;
    static boolean[][] created1;
    static boolean[][] created2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map1 = new int[n][n];
        map2 = new int[n][n];

        created1 = new boolean[n][n];
        created2 = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map1[i][j] = map2[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            count += crossColumns(map1[i]) + crossColumns(map2[i]);
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static int crossColumns(int[] map) {
        boolean[] created = new boolean[n];
        int preValue = map[0];
        for (int i = 1; i < n; i++) {
            if (Math.abs(preValue - map[i]) > 1) {
                return 0;
            }
            // 낮아질때
            if (preValue > map[i]) {
                // 끝 점이 인덱스 안에 포함되면 검사시작
                if (i + l - 1 < n) {
                    for (int j = i; j < i + l; j++) {
                        // 이미 설치했거나, 경사로 설치하는 칸의 높이가 다른 부분이 있다면 불가능
                        if (created[j] || map[j] != map[i]) {
                            return 0;
                        }
                    }
                    // 경사로 설치 위치 업데이트
                    for (int j = i; j < i + l; j++) {
                        created[j] = true;
                    }
                } else {
                    return 0;
                }
            } else if (preValue < map[i]) {
                if (i - l >= 0) {
                    for (int j = i - l; j < i; j++) {
                        if (created[j] || map[j] != map[i - l]) {
                            return 0;
                        }
                    }
                    for (int j = i - l; j < i; j++) {
                        created[j] = true;
                    }
                } else {
                    return 0;
                }
            }
            // preValue 업데이트
            preValue = map[i];
        }
        return 1;
    }
}