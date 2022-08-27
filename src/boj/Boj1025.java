package boj;

/*
    행 또는 열의 값이 등차수열로 진행되어야 함
    나올 수 있는 경우 찾기
        1. 행 + 방향, 열 0
        2. 행 - 방향, 열 0
        3. 행 + 방향 열 + 방향
        4. 행 + 방향 열 - 방향
        5. 행 - 방향 열 + 방향
        6. 행 + 방향 열 - 방향

        열이 0인 것은 + 방향 혹은 - 방향에 포함될 수 있으므로 생략하면
        총 4가지 발견
        1. 행 + 방향 열 + 방향
        2. 행 + 방향 열 - 방향
        3. 행 - 방향 열 + 방향
        4. 행 + 방향 열 - 방향

        위의 4가지 규칙을 이용해 완전탐색으로 수를 구해가며 제일 큰 완전제곱수를 찾는다.
        제일 바깥 2개의 for문은 각 배열에서의 시작점을 결정하고
        내부 2개의 for문은 -N ~ N, -M ~ M인 등차를 결정한다.
*/

import java.io.*;
import java.util.*;

class Boj1025 {
    static int n, m;
    static int[][] arr;
    static int max = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                int num = line.charAt(j) - '0';
                arr[i][j] = num;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = -n; k <= n; k++) {
                    for (int l = -m; l <= m; l++) {
                        if (k == 0 && l == 0) {
                            continue;
                        }
                        int dr = i;
                        int dc = j;
                        int num = 0;
                        while (dr >= 0 && dr < n && dc >= 0 && dc < m) {
                            num *= 10;
                            num += arr[dr][dc];
                            dr += k;
                            dc += l;
                            if (isSquareNum(num)) {
                                max = Math.max(num, max);
                            }
                        }
                    }
                }
            }
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean isSquareNum(int num) {
        return (int) Math.sqrt(num) * (int) Math.sqrt(num) == num;
    }

}