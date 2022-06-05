package boj;

import java.io.*;

/**
 * n == 3일 때
 * 한 칸의 size = n / 3인 1이된다.
 * 각 가로, 세로의 크기가 size인 블록은 9개가 나타나고,
 * 5번째 블록은 공백을 찍어야 한다.
 * <p>
 * n == 9일 때
 * 한 칸의 size -> n / 3 == 3이 된다
 * 각 가로와 세로 크기가 size인 블록은 총 9개가 나타난다.
 * 마찬가지로 5번째 블록은 공백을 찍어야 한다.
 * <p>
 * 따라서 초기 size만큼 진행시키면서 for 돌며 count를 증가시키면서
 * count 값이 5가 되면 공백을 찍고, 나머지는 모두 *을 찍는다.
 * <p>
 * 공백구간은 플래그를 세우고 재귀호출을 이용해 바로 찍어주고
 * 그렇지 않은 별 구간은 n == 1이 될때까지 재귀호출해 1이라면 그 자리에 별을찍고 종료한다.
 * <p>
 * P.S. [초기에 배열을 이용해서 값을 입력하고 출력한다는 생각을 하지못하고 바로 콘솔에 출력하려 했던 것이 생각을 복잡하게 만듦]
 */
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][n];

        printStar(arr, 0, 0, n, false);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(arr[i][j] + "");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void printStar(char[][] arr, int row, int col, int n, boolean blank) {
        if (blank) {
            for (int i = row; i < row + n; i++) {
                for (int j = col; j < col + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }
        if (n == 1) {
            arr[row][col] = '*';
            return;
        }
        // size 크기만큼 증가하면서 count를 하고, 만약 count==5라면 공백을 입력, 나머지는 별 입력
        int size = n / 3;
        int count = 0;
        for (int i = row; i < row + n; i += size) {
            for (int j = col; j < col + n; j += size) {
                count++;
                if (count == 5) {
                    printStar(arr, i, j, size, true);
                } else {
                    printStar(arr, i, j, size, false);

                }
            }
        }
    }
}