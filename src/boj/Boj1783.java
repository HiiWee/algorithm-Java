package boj;

import java.util.*;
import java.io.*;

class Boj1783_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        // 세로가 1, 2, 3 이상인 경우 조건이 나뉨
        int maxVisit = 1;
        if (row == 2) {
            // 세로가 2칸이라면 2, 3만 사용가능 하지만 4방법 모두 사용 불가하므로 5개를 넘으면 4로 초기화
            if ((col + 1) / 2 < 5) {
                maxVisit = (col + 1) / 2;
            } else {
                maxVisit = 4;
            }
        } else if (row >= 3) {
            if (col >= 7) {
                // 7칸은 4가지 방법을 사용하는데 사용, 이후 1, 4번만 사용하면 1개의 열당 1칸씩 증가됨
                maxVisit = 5 + (col - 7);
            } else {
                if (col < 5) {
                    maxVisit = col;
                } else {
                    maxVisit = 4;
                }
            }
        }
        bw.write(maxVisit + "\n");
        bw.flush();
        bw.close();
    }
}

class Boj1783_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 세로가 1, 2, 3 이상인 경우 조건이 나뉨
        bw.write(calc(N, M) + "\n");
        bw.flush();
        bw.close();
    }

    public static int calc(int n, int m) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return Math.min(4, (m + 1) / 2);
        }
        if (m >= 7) {
            return m - 2;
        }
        return Math.min(4, m);
    }
}