package boj;

import java.io.*;
import java.util.*;

class Boj15683 {

    static class Node {
        int number, r, c;
        int[] dir;
        int spinLimit;

        public Node(int number, int r, int c, int[] dir) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.spinLimit = findLimit();
        }

        public int findLimit() {
            if (number == 1) {
                return 4;
            } else if (number == 2) {
                return 2;
            } else if (number == 3) {
                return 4;
            } else if (number == 4) {
                return 4;
            } else {
                return 1;
            }
        }
    }

    static final int[][] directions = {
            {1}, {3, 1}, {0, 1}, {3, 0, 1}, {0, 1, 2, 3}
    };
    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static final List<Node> cctv = new ArrayList<>();
    static int n, m, minCount = Integer.MAX_VALUE;
    static int[][] map;

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
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctv.add(new Node(map[i][j], i, j, directions[map[i][j] - 1]));
                }
            }
        }

        search(new Node[cctv.size()], 0, cctv.size());

        bw.write(minCount + "");
        bw.flush();
        bw.close();
    }

    public static void search(Node[] selectCctv, int index, int size) {
        if (index == size) {
            updateBlindSpot(selectCctv);
            return;
        }
        // 현재 CCTV를 가져옴
        Node curCctv = cctv.get(index);
        // CCTV가 회전할 수 있는 최대 수를 돌면서
        for (int i = 0; i < curCctv.spinLimit; i++) {
            int[] newDirections = new int[curCctv.dir.length];
            // 회전이후 새로운 방향들을 담고
            for (int j = 0; j < newDirections.length; j++) {
                newDirections[j] = (curCctv.dir[j] + i) % 4;
            }
            // 현재 인덱스의 CCTV를 저장
            selectCctv[index] = new Node(curCctv.number, curCctv.r, curCctv.c, newDirections);
            // 이후 다음 CCTV를 탐색하도록 진행
            search(selectCctv, index + 1, size);
        }
    }

    public static void updateBlindSpot(Node[] selectCctv) {
        int[][] copyMap = copyMap();
        for (int i = 0; i < selectCctv.length; i++) {
            fill(copyMap, selectCctv[i]);
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    count++;
                }
            }
        }
        minCount = Math.min(count, minCount);
    }

    public static void fill(int[][] map, Node curCctv) {
        for (int dir : curCctv.dir) {
            int nextR = curCctv.r;
            int nextC = curCctv.c;
            while (true) {
                nextR += rows[dir];
                nextC += cols[dir];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) {
                    break;
                }
                if (map[nextR][nextC] == 6) {
                    break;
                }
                if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = -1;
                }
            }
        }
    }

    public static int[][] copyMap() {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }
}