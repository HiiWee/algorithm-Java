package boj;
/**
 * 원판의 개수가 3(3, 2, 1 순서대로 쌓여있음)개이고, 기둥이 A, B, C 순서대로 존재한다면
 *
 * if (N == 1)
 *      start -> to로 원판이 이동함
 * else
 *      1. 먼저 1, 2번 원판을 B로 이동하는 과정을 하고
 *      2. 3번 원판을 C로 이동한다.
 *      3. 이후 다시 B의 1, 2번 원판을 C로 이동해야 한다.
 *
 * [참고: https://shoark7.github.io/programming/algorithm/tower-of-hanoi]
 */
import java.io.*;

class Boj11729 {
    static int moveCount = 0;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        solveHanoi(n, "1", "2", "3");

        bw.write(moveCount + "\n");
        bw.write(sb + "");
        bw.flush();
        bw.close();
    }

    public static void solveHanoi(int N, String start, String via, String to) {
        if (N == 1) {
            move(start, to);
        } else {
            solveHanoi(N - 1, start, to, via);
            move(start, to);
            solveHanoi(N - 1, via, start, to);
        }
    }
    public static void move(String start, String to) {
        sb.append(start).append(" ").append(to).append("\n");
        moveCount++;
    }
}