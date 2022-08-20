package boj;
/*
    2중으로 반복문을 돌며
    2개의 스티커를 선택했을때 가능한지 판별한다.
    붙일수있음을 판별하는 방법
        1. 1번 스티커를 그냥 붙이기 or 돌려서 붙이기에서 각각의 경우에
        2. 2번 스티커를 그냥 붙일지 혹은 돌려서 붙일지를 생각해야 한다.
        3. 또한 그 경우에 스티커를 가로로 붙일지 또는 세로로 붙일지도 결정해야한다

   이렇게 모든 스티커를 확인하여 가장 큰 값을 구하고, 어떤 값도 구해지지 않았다면 0 출력
*/

import java.util.*;
import java.io.*;

class Boj16937 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int a1 = arr[i][0];
            int b1 = arr[i][1];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int a2 = arr[j][0];
                int b2 = arr[j][1];
                if (isPossibleToPut(a1, b1, H, W)) {
                    if (isPossibleToPut(a2, b2, H - a1, W) || isPossibleToPut(b2, a2, H - a1, W)) {
                        max = Math.max(max, a1 * b1 + a2 * b2);
                    } else if (isPossibleToPut(a2, b2, H, W - b1) || isPossibleToPut(b2, a2, H, W - b1)) {
                        max = Math.max(max, a1 * b1 + a2 * b2);
                    }
                }
                if (isPossibleToPut(b1, a1, H, W)) {
                    if (isPossibleToPut(a2, b2, H, W - a1) || isPossibleToPut(b2, a2, H, W - a1)) {
                        max = Math.max(max, a1 * b1 + a2 * b2);
                    } else if (isPossibleToPut(a2, b2, H - b1, W) || isPossibleToPut(b2, a2, H - b1, W)) {
                        max = Math.max(max, a1 * b1 + a2 * b2);
                    }
                }
            }
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean isPossibleToPut(int r, int c, int h, int w) {
        if (r <= h && c <= w) {
            return true;
        }
        return false;
    }
}