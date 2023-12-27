package boj;

// 백트래킹 -> N, M값이 크지 않아 가능하지만 비효율적인 풀이
import java.io.*;
import java.util.*;

class Boj13460_1 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static int n, m;
    static char[][] map;
    static Node goal;
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        Node red = null;
        Node blue = null;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    red = new Node(i, j);
                } else if (map[i][j] == 'B') {
                    blue = new Node(i, j);
                } else if (map[i][j] == 'O') {
                    goal = new Node(i, j);
                }
            }
        }

        moveBalls(red, blue, 0);
        if (minCount == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(minCount + "");
        }
        bw.flush();
        bw.close();
    }

    public static void moveBalls(Node red, Node blue, int count) {
        // 10번을 넘기면 안됨
        if (count > 10) {
            return;
        }
        // 파란볼이 빠지면 실패
        if (blue.r == goal.r && blue.c == goal.c) {
            return;
        }
        // 도착했는데 파란볼도 빠지면 안됨
        if (red.r == goal.r && red.c == goal.c) {
            minCount = Math.min(minCount, count);
            return;
        }
        for (int i = 0; i < 4; i++) {
            boolean redGoal = false;
            boolean blueGoal = false;
            boolean redStop = false;
            boolean blueStop = false;
            int nRedR = red.r;
            int nRedC = red.c;
            int nBlueR = blue.r;
            int nBlueC = blue.c;
            while (true) {
                int preRedR = nRedR;
                int preRedC = nRedC;
                int preBlueR = nBlueR;
                int preBlueC = nBlueC;
                if (!redStop) {
                    nRedR += rows[i];
                    nRedC += cols[i];
                }
                if (!blueStop) {
                    nBlueR += rows[i];
                    nBlueC += cols[i];
                }
                // 빨간공이 장애물을 만났을 경우 빨간공 롤백
                if (!redStop && !isPossible(nRedR, nRedC)) {
                    redStop = true;
                    nRedR = preRedR;
                    nRedC = preRedC;
                }
                // 파란공이 장애물을 만났을 경우 파란공 롤백
                if (!blueStop && !isPossible(nBlueR, nBlueC)) {
                    blueStop = true;
                    nBlueR = preBlueR;
                    nBlueC = preBlueC;
                }
                // 둘다 골이 아니고, 빨간공과 파란공이 겹치는 경우 둘 다 롤백
                if ((!redGoal && !blueGoal) && nRedR == nBlueR && nRedC == nBlueC) {
                    redStop = true;
                    blueStop = true;
                    nRedR = preRedR;
                    nRedC = preRedC;
                    nBlueR = preBlueR;
                    nBlueC = preBlueC;
                }
                if (!redStop && nRedR == goal.r && nRedC == goal.c) {
                    redGoal = true;
                    redStop = true;
                }
                if (!blueStop && nBlueR == goal.r && nBlueC == goal.c) {
                    blueGoal = true;
                    blueStop = true;
                }
                if (redStop && blueStop) {
                    break;
                }
            }
            moveBalls(new Node(nRedR, nRedC), new Node(nBlueR, nBlueC), count + 1);
        }
    }

    public static boolean isPossible(int r, int c) {
        if (map[r][c] == '#') {
            return false;
        }
        return true;
    }
}

// BFS 사용 -> 효율적인 풀이
class Boj13460_2 {

    static class Node {
        int rr, rc, br, bc, count;

        public Node(int rr, int rc, int br, int bc, int count) {
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
            this.count = count;
        }
    }

    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static int n, m;
    static char[][] map;
    static Node goal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        Node startNode = new Node(0, 0, 0, 0, 0);
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    startNode.rr = i;
                    startNode.rc = j;
                } else if (map[i][j] == 'B') {
                    startNode.br = i;
                    startNode.bc = j;
                }
            }
        }

        bw.write(moveBalls(startNode) + "");
        bw.flush();
        bw.close();
    }

    public static int moveBalls(Node startNode) {
        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[startNode.rr][startNode.rc][startNode.br][startNode.bc] = true;
        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            // 카운트가 10이 넘어가면 나머지 큐에 있는 것들은 모두 10이 넘었다는 의미이므로 그대로 종료

            if (curNode.count > 10) {
                return -1;
            }
            // 파란공이 목적지에 도착하면 실패이므로 패스
            if (map[curNode.br][curNode.bc] == 'O') {
                continue;
            }
            if (map[curNode.rr][curNode.rc] == 'O') {
                return curNode.count;
            }

            for (int i = 0; i < 4; i++) {
                int nextRedR = curNode.rr;
                int nextRedC = curNode.rc;

                while (true) {
                    nextRedR += rows[i];
                    nextRedC += cols[i];

                    if (!isPossible(nextRedR, nextRedC)) {
                        break;
                    }
                }
                // 벽에 도달한 경우만 롤백
                if (map[nextRedR][nextRedC] == '#') {
                    nextRedR -= rows[i];
                    nextRedC -= cols[i];
                }

                int nextBlueR = curNode.br;
                int nextBlueC = curNode.bc;

                while (true) {
                    nextBlueR += rows[i];
                    nextBlueC += cols[i];

                    if (!isPossible(nextBlueR, nextBlueC)) {
                        break;
                    }
                }
                // 벽에 도달한 경우만 롤백
                if (map[nextBlueR][nextBlueC] == '#') {
                    nextBlueR -= rows[i];
                    nextBlueC -= cols[i];
                }


                // 동일한 위치인 경우 더 많이 이동한 점에 대해서 롤백함, 다만 O에 도착하면 하지 않음
                if (nextRedR == nextBlueR && nextRedC == nextBlueC && map[nextRedR][nextRedC] != 'O') {
                    int redDist = Math.abs(curNode.rr - nextRedR) + Math.abs(curNode.rc - nextRedC);
                    int blueDist = Math.abs(curNode.br - nextBlueR) + Math.abs(curNode.bc - nextBlueC);

                    if (redDist < blueDist) {
                        nextBlueR -= rows[i];
                        nextBlueC -= cols[i];
                    } else {
                        nextRedR -= rows[i];
                        nextRedC -= cols[i];
                    }
                }

                if (!visited[nextRedR][nextRedC][nextBlueR][nextBlueC]) {
                    que.offer(new Node(nextRedR, nextRedC, nextBlueR, nextBlueC, curNode.count + 1));
                    visited[nextRedR][nextRedC][nextBlueR][nextBlueC] = true;
                }
            }
        }
        return -1;
    }

    public static boolean isPossible(int r, int c) {
        if (map[r][c] == '#' || map[r][c] == 'O') {
            return false;
        }
        return true;
    }
}