package boj;

import java.io.*;

class Boj9663 {

    static int n;
    static int[] map;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n];

        nQueen(0);

        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    // 배열의 인덱스가 열, 배열 인덱스 위치의 요소가 행
    public static void nQueen(int depth) {
        // n열까지 탐색을 마치면 카운팅함
        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            // depth열에 i행을 일단 대입
            map[depth] = i;

            // 위 행의 입력이 가능한지 이전까지 입력한 열들을 확인하여 체크함
            if (isPossible(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    public static boolean isPossible(int col) {
        for (int i = 0; i < col; i++) {
            // 열의 위치 자체가 다르므로 같은 행인지만 확인
            if (map[i] == map[col]) {
                return false;
            }

            // 대각선인지 확인, 대각선에 포함되는 두 점의 각 행과 열의 차의 절댓값을 동일함(항상 동일한 벡터값으로 증감해야 대각선이므로)
            if (Math.abs(i - col) == Math.abs(map[i] - map[col])) {
                return false;
            }
        }
        return true;
    }
}