package boj;

import java.io.*;
import java.util.*;

class Boj3190 {

    // 상 우 하 좌
    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static class Node {
        int r, c, dir;

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static class Snake {
        Deque<Node> snakes = new ArrayDeque<>();

        public Snake() {
            // 시작점 등록
            snakes.offer(new Node(0, 0, 1));
        }

        public boolean moveNext() {
            Node head = snakes.peek();
            int nextR = head.r + rows[head.dir];
            int nextC = head.c + cols[head.dir];
            // 벽에 부딪히거나
            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                return false;
            }
            // 자기 몸에 부딪히면 실패
            if (map[nextR][nextC] == 2) {
                return false;
            }
            // 일단 몸길이를 늘려 다음칸에 머리를 유지시킴
            snakes.offerFirst(new Node(nextR, nextC, head.dir));

            // 다음칸이 사과가 아니라면 꼬리를 제거
            if (map[nextR][nextC] != 1) {
                Node tail = snakes.pollLast();
                map[tail.r][tail.c] = 0;
            }
            map[nextR][nextC] = 2;
            return true;
        }

        public void turnTo(int dirAmount) {
            Node head = snakes.peek();
            head.dir = (head.dir + dirAmount + 4) % 4;
        }
    }

    static int n, k;
    static int[][] map;
    static Snake snake = new Snake();
    static Queue<int[]> commands = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];
        map[0][0] = 2; // 시작위치
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }


        int l = Integer.parseInt(br.readLine());
        while (l-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            int command;
            char direction = st.nextToken().charAt(0);
            // 왼쪽은 dir값이 감소하는 방향이므로
            if (direction == 'L') {
                command = -1;
            } else {
                command = 1;
            }
            commands.offer(new int[]{second, command});
        }

        int second = 0;
        while (true) {
            second++;
            if (!moveSnake()) {
                break;
            }
            // 방향전환 시간이 현재 시간과 동일하다면
            if (!commands.isEmpty() && commands.peek()[0] == second) {
                snake.turnTo(commands.poll()[1]);
            }
        }

        bw.write(Integer.toString(second));
        bw.flush();
        bw.close();
    }

    public static boolean moveSnake() {
        // 뱀의 머리를 이동시킴
        boolean isSuccess = snake.moveNext();
        if (!isSuccess) {
            return false;
        }
        return true;
    }
}
