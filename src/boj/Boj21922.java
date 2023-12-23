package boj;

/*
   가능한 곳은 모두 탐색해야 함
   상 하 좌 우 한 방향으로 쭉 탐색하면서 더이상 전진할 수 없을떄까지 해야 함
*/
import java.io.*;
import java.util.*;

class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int n, m;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        List<Node> airNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    visited[i][j] = true;
                    airNodes.add(new Node(i, j));
                }
            }
        }

        for (Node node : airNodes) {
            for (int i = 0; i < 4; i++) {
                int nextR = rows[i] + node.r;
                int nextC = cols[i] + node.c;                ;
                moveForward(node, new Node(nextR, nextC), i);
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }
        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    public static void moveForward(Node startNode, Node curNode, int dir) {
        // 사이클을 돌면 종료
        if (startNode.r == curNode.r && startNode.c == curNode.c) {
            return;
        }
        // 올바른 좌표가 아니면 종료
        if (!isPossible(curNode.r, curNode.c)) {
            return;
        }
        visited[curNode.r][curNode.c] = true;
        // 물건이 있을 경우에만 방향 스위치
        if (map[curNode.r][curNode.c] >= 1 && map[curNode.r][curNode.c] <= 4) {
            dir = getNextDirection(curNode, dir);
        }
        int nextR = curNode.r + rows[dir];
        int nextC = curNode.c + cols[dir];
        moveForward(startNode, new Node(nextR, nextC), dir);
    }

    public static int getNextDirection(Node node, int dir) {
        int thing = map[node.r][node.c];
        if (dir == 0) {
            if (thing == 1) {
                return dir;
            } else if (thing == 2) {
                return 1;
            } else if (thing == 3) {
                return 3;
            } else {
                return 2;
            }
        } else if (dir == 1) {
            if (thing == 1) {
                return dir;
            } else if (thing == 2) {
                return 0;
            } else if (thing == 3) {
                return 2;
            } else {
                return 3;
            }
        } else if (dir == 2) {
            if (thing == 1) {
                return 3;
            } else if (thing == 2) {
                return 2;
            } else if (thing == 3) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (thing == 1) {
                return 2;
            } else if (thing == 2) {
                return 3;
            } else if (thing == 3) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static boolean isPossible(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) {
            return false;
        }
        // 에어컨이 이미 위치했는데 방문까지 했다면 더 할 필요가 없음
        if (map[r][c] == 9 && visited[r][c]) {
            return false;
        }
        return true;
    }
}