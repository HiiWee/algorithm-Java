package boj;

// 비효율적인 풀이
import java.io.*;
import java.util.*;

class Boj2638_1 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    totalCount++;
                }
            }
        }

        if (totalCount == 0) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }
        int airLevel = 0;
        int level = 1;
        while (true) {
            airLevel--;
            level++;

            // 공기를 퍼트리고
            airBfs(0, 0, airLevel);

            // 치즈를 녹임
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] > 0 && map[i][j] < level) {
                        totalCount -= cheeseBfs(i, j, level, airLevel);
                    }
                }
            }
            // 한번도 안녹으면 전부 다 녹은것
            if (totalCount == 0) {
                break;
            }
        }

        bw.write(Integer.toString(level - 1));
        bw.flush();
        bw.close();
    }

    public static int cheeseBfs(int r, int c, int level, final int airLevel) {
        List<Node> meltedCheeses = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(r, c));
        map[r][c] = level;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            int airCount = 0;
            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR >= n || nextR < 0 || nextC >= m || nextC < 0) {
                    continue;
                }
                if (map[nextR][nextC] < 0) {
                    airCount++;
                } else if (map[nextR][nextC] > 0 && map[nextR][nextC] < level) {
                    map[nextR][nextC] = level;
                    que.offer(new Node(nextR, nextC));
                }
            }

            if (airCount >= 2) {
                meltedCheeses.add(curNode);
            }
        }

        for (Node cheese : meltedCheeses) {
            map[cheese.r][cheese.c] = airLevel;
        }
        return meltedCheeses.size();
    }

    public static void airBfs(int r, int c, int level) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(r, c));
        map[r][c] = level;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR >= n || nextR < 0 || nextC >= m || nextC < 0) {
                    continue;
                }
                if (map[nextR][nextC] > 0) {
                    continue;
                }
                if (map[nextR][nextC] <= 0 && map[nextR][nextC] > level) {
                    map[nextR][nextC] = level;
                    que.offer(new Node(nextR, nextC));
                }
            }
        }
    }
}

// 효율적인 풀이
class Boj2638_2 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m;
    static int[][][] map;
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if (map[i][j][0] == 1) {
                    totalCount++;
                }
            }
        }
        int second = airBfs();

        bw.write(Integer.toString(second));
        bw.flush();
        bw.close();
    }

    public static int airBfs() {
        Queue<Node> que = new LinkedList<>();
        Queue<Node> nextQue = new LinkedList<>();

        que.offer(new Node(0, 0));
        map[0][0][0] = -1;
        int second = 0;
        while (totalCount > 0) {
            while (!que.isEmpty()) {
                Node curNode = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nextR = curNode.r + rows[i];
                    int nextC = curNode.c + cols[i];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                        continue;
                    }

                    // 공기라면 계속 진행
                    if (map[nextR][nextC][0] == 0) {
                        que.offer(new Node(nextR, nextC));
                        map[nextR][nextC][0] = -1;
                    } else if (map[nextR][nextC][0] == 1) {
                        // 치즈라면 공기가 닿은 횟수 카운팅
                        map[nextR][nextC][1]++;
                        // 공기가 2번 닿았다면 치즈를 녹이고, 다음 큐에 삽입
                        if (map[nextR][nextC][1] == 2) {
                            map[nextR][nextC][0] = -1;
                            totalCount--;
                            nextQue.offer(new Node(nextR, nextC));
                        }
                    }
                }
            }
            second++;
            que.addAll(nextQue);
            nextQue.clear();
        }

        return second;
    }
}