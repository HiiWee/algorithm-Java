package boj;

/*
    첫번째 방법 == 시간초과
    1. 정사각형 블럭을 계산해 부족한 숫자 파악 만약 1개라면 채우고 아니라면 2번 진행
    2. 가로를 확인해 들어갈 수 있는 숫자 파악하고 1번하고 비교 만약 1개라면 작성 아니라면 3번
    3. 세로를 확인해 모두 공통으로 들어갈 수 있는 숫자 파악하고 작성

    방법 생각
    어떻게 정사각형의 범위를 파악? 9 X 9의 정사각형 판이므로 3씩 끊어서 확인 9개로 나눌 수 있음

    과정:
    일단 3번의 검사를 해도 만족되지 않는다면 큐에 담고 다음 것을 진행,
    위의 과정 반복하면 완료될듯?
    >> 시간초과

    두번째 방법 재귀이용
    특징으로 계속 열을 증가시키면서 알맞은 수를 찾다가 최종적으로 올바른 정답이 되면
    그대로 출력하고 시스템을 종료시킨다.
    만약 그렇지 않고 재귀를 벗어나게 되면 다시 해당 수를0으로 초기화하고 처음부터 그 수를 탐색

*/
import java.util.*;
import java.io.*;

class Boj2580 {
    static int[][] map;
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        sudoku(0, 0);

    }


    public static void sudoku(int r, int c) throws IOException {
        // 열을 초과함 == 한 행을 모두 탐방, 따라서 다음 행으로 이동
        if (c == 9) {
            sudoku(r + 1, 0);
            return;
        }
        // 행을 초과함 == 모든 9 * 9의 행열 탐방 완료
        if (r == 9) {
            print();

            System.exit(0);
        }

        if (map[r][c] == 0) {
            for (int i = 1; i < 10; i++) {
                if (isPossible(r, c, i)) {
                    map[r][c] = i;
                    sudoku(r, c + 1);
                }
            }
            // 만약 끝까지 올바른 수가 들어간다면 System.exit(0)를 만나서 이곳을 들를일이 없다.
            // 위의 sydoku함수가 종료되고 이곳으로 온다는 스도쿠의 수가 올바르지 않다는 의미이므로
            // 0을 저장해 해당 재귀를 종료하고 상위 재귀로 돌아간 후 다음 숫자부터 재 검사
            map[r][c] = 0;
            return;
        }
        // 특정 행에 0 인수가 없다면 이곳을 들름
        sudoku(r, c + 1);
    }

    public static boolean isPossible(int r, int c, int value) {
        int row = (r / 3) * 3;
        int col = (c / 3) * 3;

        // 정사각형 탐색
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                if (value == map[i][j]) {
                    return false;
                }
            }
        }
        // 속한 행 탐색
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == value) {
                return false;
            }
        }

        // 속한 열 탐색
        for (int i = 0; i < 9; i++) {
            if (map[i][c] == value) {
                return false;
            }
        }
        return true;
    }

    public static void print() throws IOException {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
