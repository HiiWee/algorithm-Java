package boj;

import java.io.*;

/**
 * 제일 작은 별의 기준이 n = 3일 경우이다.
 * 이 때 for와 if를 가지고 어떤 규칙성을 찾기 보다는
 * 좌표값을 직접 정해주면서 별을 찍는것이 효율적일 수 도 있다는걸 알아두자
 */
class Main {
    // 별을 찍는 위치를 결정함
    static int[] dy = {0, 1, 1, 2, 2, 2, 2, 2};
    static int[] dx = {0, -1, 1, -2, -1, 0, 1, 2};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][2 * n - 1];

        printStar(arr, 0, n - 1, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                if (arr[i][j] == '*') {
                    bw.write("*");
                } else {
                    bw.write(' ');
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void printStar(char[][] arr, int row, int col, int n) {
        // n값이 3이라면 해당 위치에서부터 삼각형을 그리기 시작한다.
        if (n == 3) {
            for (int i = 0; i < 8; i++) {
                arr[row + dy[i]][col + dx[i]] = '*';
            }
            return;
        }

        int nextSize = n / 2;
        // 좌표값을 계산 제일 꼭대기 가운데 별을 기준점으로 두고 하위 2개의 삼각형의 좌표를 구함
        printStar(arr, row, col, nextSize);
        printStar(arr, row + nextSize, col - nextSize, nextSize);
        printStar(arr, row + nextSize, col + nextSize, nextSize);
    }
}

