package boj;

/*
    1. 스티커를 회전시키지 않고 모눈종이에서 떼어낸다.
    2. 다른 스티커와 겹치거나 노트북을 벗어나지 않으면서 스티커를 붙일 수 있는 위치를 찾는다.
       혜윤이는 노트북의 위쪽부터 스티커를 채워 나가려고 해서, 스티커를 붙일 수 있는 위치가 여러 곳 있다면 가장 위쪽의 위치를 선택한다.
       가장 위쪽에 해당하는 위치도 여러 곳이 있다면 그중에서 가장 왼쪽의 위치를 선택한다.
    3. 선택한 위치에 스티커를 붙인다. 만약 스티커를 붙일 수 있는 위치가 전혀 없어서 스티커를 붙이지 못했다면,
       스티커를 시계 방향으로 90도 회전한 뒤 2번 과정을 반복한다.
    4. 위의 과정을 네 번 반복해서 스티커를 0도, 90도, 180도, 270도 회전시켜 봤음에도 스티커를 붙이지 못했다면 해당 스티커를 붙이지 않고 버린다.

    위의 과정을 모든 스티커에서 완료하게 된 이후 스티커가 붙은 곳의 넓이를 측정한다.
*/

import java.io.*;
import java.util.*;

class Boj18808 {
    static int n, m, k; // 세로, 가로, 스티커수
    static int[][] map;
    static int[][][] sticker;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        sticker = new int[k][][];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sticker[i] = new int[r][c];

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int h = 0; h < c; h++) {
                    sticker[i][j][h] = Integer.parseInt(st.nextToken());
                }
            }
        }

        stick();

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    count++;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void stick() {

        // 하나의 스티커를 기준으로 제일 끝까지 탐색하고 그래도 없다면 90도 회전
        // 270도까지 테스트해도 없다면 그제서야 다음 스티커를 탐색해야 한다. --> 중요!!
        for (int st = 0; st < k; st++) {
            int count = 0;
            loop:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (check(i, j, st)) {
                        attach(i, j, st);
                        break loop;
                    } else if (i == n - 1 && j == m - 1) {
                        count++;
                        if (count < 4) {
                            i = 0;
                            j = -1;
                            rotate(st);
                        } else {
                            break loop;
                        }
                    }
                }
            }
        }

    }

    public static boolean check(int r, int c, int st) {
        int[][] arr = sticker[st];
        // 스티커를 붙였을때 모눈종이를 벗어나면 안됨
        if (r + arr.length > n || c + arr[0].length > m) {
            return false;
        }
        for (int i = r; i < arr.length + r; i++) {
            for (int j = c; j < arr[0].length + c; j++) {
                // 둘다 1 이라면 스티커가 겹치게 되므로 붙일 수 없다.
                if (arr[i - r][j - c] == 1 && map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void attach(int r, int c, int st) {
        int[][] arr = sticker[st];

        for (int i = r; i < r + arr.length; i++) {
            for (int j = c; j < c + arr[0].length; j++) {
                if (arr[i - r][j - c] == 1) {
                    map[i][j] = arr[i - r][j - c];
                }
            }
        }
    }

    public static void rotate(int st) {
        // 90도 회전
        int[][] temp = new int[sticker[st][0].length][sticker[st].length];

        int r = 0;
        for (int i = 0; i < sticker[st][0].length; i++) {
            int c = 0;
            for (int j = sticker[st].length - 1; j >= 0; j--) {
                temp[r][c++] = sticker[st][j][i];
            }
            r++;
        }
        sticker[st] = temp;
    }
}