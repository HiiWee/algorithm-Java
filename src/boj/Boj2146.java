package boj;

import java.io.*;
import java.util.*;

// 시간 적게 걸리나 메모리 사용률 높음
class Boj2146_1 {
    static int n;
    static int[][] map;
    static int[][] countMap;
    static boolean[][] visited;
    static Queue<P> que;
    static List<P> list;
    static StringTokenizer st;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    // 좌표 담을 클래스
    static class P {
        int y, x;

        P(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        que = new LinkedList<>();
        list = new ArrayList<>();
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        countMap = new int[n][n];
        visited = new boolean[n][n];

        // map초기화 하면서 list에 1인 좌표 모두 담음
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 1) {
                    list.add(new P(i, j));
                }
            }
        }
        // 각 섬을 서로 다른 번호로 명명
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    fillLand(i, j, num);
                    num++;
                }
            }
        }
        int min = BFS();
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    // 해안가쪽 땅인지 체크
    public static boolean isCornerLand(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int nextY = row[i] + y;
            int nextX = col[i] + x;

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
                continue;
            }
            if (map[nextY][nextX] == 0) {
                return true;
            }
        }
        return false;
    }

    // 섬을 각각 다른 번호로 채움
    public static void fillLand(int y, int x, int num) {
        visited[y][x] = true;
        map[y][x] = num;
        for (int i = 0; i < 4; i++) {
            int nextRow = y + row[i];
            int nextCol = x + col[i];
            if (nextRow < 0 || nextCol < 0 || nextCol >= n || nextRow >= n) {
                continue;
            }
            if (!visited[nextRow][nextCol] && map[nextRow][nextCol] != 0) {
                fillLand(nextRow, nextCol, num);
            }

        }
    }

    // 해안가땅을 돌며 다른 섬을 만나면 거리를 측정 후 최소거리 업데이트
    public static int BFS() {
        int min = Integer.MAX_VALUE;

        for (P p : list) {
            if (!isCornerLand(p.y, p.x)) {
                continue;
            }
            visited = new boolean[n][n];
            countMap = new int[n][n];
            que.add(new P(p.y, p.x));
            int island = map[p.y][p.x];
            while (!que.isEmpty()) {
                P nP = que.poll();
                for (int i = 0; i < 4; i++) {
                    int nextRow = row[i] + nP.y;
                    int nextCol = col[i] + nP.x;

                    if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) {
                        continue;
                    }
                    if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 0) {
                        que.add(new P(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                        countMap[nextRow][nextCol] = countMap[nP.y][nP.x] + 1;
                    } else if (!visited[nextRow][nextCol] && map[nextRow][nextCol] != island) {
                        // 다른 섬에 도달하는 순간이 최소 길이이므로 반복문 벗어남
                        min = Math.min(min, countMap[nP.y][nP.x]);
                        que.clear();
                        break;
                    }
                }
            }
        }
        return min;
    }
}

class Boj2146_2 {
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    static class P {
        int y, x;

        P(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        // 섬 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬의 명명을 서로 다르게 함
        int islandCount = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    fillIsland(i, j, islandCount);
                    islandCount++;
                }
            }
        }

        // 하나의 섬부터 다른 섬까지 이어지는 최소 거리를 순차적으로 탐색하고 다음 섬으로 넘어감
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < islandCount; i++) {
            min = Math.min(BFS(i), min);
            // visited 초기화
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    visited[j][k] = false;
                }
            }
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();

    }

    //
    public static void fillIsland(int y, int x, int num) {
        visited[y][x] = true;
        map[y][x] = num;
        for (int i = 0; i < 4; i++) {
            int ny = row[i] + y;
            int nx = col[i] + x;
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                continue;
            }
            if (!visited[ny][nx] && map[ny][nx] != 0) {
                fillIsland(ny, nx, num);
            }
        }
    }

    public static int BFS(int islandCount) {
        int min = 0;
        Queue<P> que = new LinkedList<>();
        // 하나의 섬의 좌표를 모두 담음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (islandCount == map[i][j]) {
                    que.add(new P(i, j));
                }
            }
        }

        // 하나의 섬부터 탐방을 시작하는데 섬의 외곽 좌표를 순차적으로 돌다가
        // 처음으로 다른 섬에 도착하는 길이가 최소값이므로 바로 반환함
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                P p = que.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = p.y + row[j];
                    int nx = p.x + col[j];
                    if (ny >= 0 && ny < n && nx >= 0 && nx < n) {
                        if (map[ny][nx] != islandCount && map[ny][nx] != 0) {
                            return min;
                        }
                        if (!visited[ny][nx] && map[ny][nx] == 0) {
                            que.add(new P(ny, nx));
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            min++;
        }
        return min;
    }
}