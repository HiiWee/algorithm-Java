package boj;

/*
    상어는 같은 방향으로 동시에 이동해야 하므로, 이동할 위치를 결정하고
    한번에 이동시켜야, 갈 수 있는 방향인데 가지 못하는 경우가 생기지 않습니다.
*/
import java.io.*;
import java.util.*;

class Boj19237 {

    static class Node {
        int r, c, dir;
        Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static class Shark {
        int r, c, dir;
        boolean isOut;

        Shark(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public void moveTo(Node node) {
            this.r = node.r;
            this.c = node.c;
            this.dir = node.dir;
        }
    }

    static class Smell {
        int sharkNumber, k;

        Smell(int sharkNumber, int k) {
            this.sharkNumber = sharkNumber;
            this.k = k;
        }
    }

    // 상하좌우
    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};

    static int[][][] sharkMoves;

    static int n, m, k;
    static int[][] map;
    static Smell[][] smellMap;
    static Shark[] sharks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sharkMoves = new int[m + 1][4][4];
        sharks = new Shark[m + 1];

        map = new int[n][n];
        smellMap = new Smell[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    smellMap[i][j] = new Smell(map[i][j], k);
                    sharks[map[i][j]] = new Shark(i, j, -1);
                }
            }
        }
        // 상어 초기 방향 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        // 각 상어들의 방향당 우선순위 방향 입력 받기
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharkMoves[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int second = 0;
        while (true) {
            second++;
            if (second > 1000) {
                second = -1;
                break;
            }
            // 1. 상어가 이동할 위치 선정하기
            Node[] nodes = findNextPosition();
            // 2. 상어의 이동 시작, 이때 상어가 한칸에 둘 이상 들어있는 경우를 체크하며 더 낮은 번호의 상어만 남도록함
            moveShark(nodes);
            // 3. 냄새 뿌리기 전에 기존 냄새 값들 1씩 감소
            eraseSmell();
            // 4. 냄새 뿌리기
            spreadSmell();
            // 5. 상어 몇마리 남았는지 체크하기
            int count = 0;
            for (int i = 1; i <= m; i++) {
                if (!sharks[i].isOut) {
                    count++;
                }
            }
            // 6. 1번상어만 남았을경우 종료
            if (count == 1 && !sharks[1].isOut) {
                break;
            }
        }

        bw.write(second + "");
        bw.flush();
        bw.close();
    }

    public static void spreadSmell() {
        // 자기자신의 위치로 간 경우는 냄새값을 k로 초기화 시켜줌
        for (int i = 1; i <= m; i++) {
            Shark shark = sharks[i];
            if (shark.isOut) {
                continue;
            }
            smellMap[shark.r][shark.c] = new Smell(i, k);
        }
    }

    public static void eraseSmell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (smellMap[i][j] == null) {
                    continue;
                }
                smellMap[i][j].k--;
                if (smellMap[i][j].k == 0) {
                    smellMap[i][j] = null;
                }
            }
        }
    }

    public static void moveShark(Node[] nodes) {
        for (int i = 1; i <= m; i++) {
            Shark shark = sharks[i];
            Node node = nodes[i];
            if (node == null) {
                continue;
            }
            // 다른 상어가 있다면
            if (map[node.r][node.c] != 0) {
                // 현재 샤크가 더 작은 번호를 가지면 현재 샤크가 해당 자리 차지
                if (map[node.r][node.c] > i)  {
                    map[shark.r][shark.c] = 0;
                    shark.moveTo(node);
                    sharks[map[node.r][node.c]].isOut = true;
                    map[node.r][node.c] = i;
                } else {
                    map[shark.r][shark.c] = 0;
                    shark.isOut = true;
                }
            } else {
                map[shark.r][shark.c] = 0;
                map[node.r][node.c] = i;
                shark.moveTo(node);
            }
        }
    }

    public static Node[] findNextPosition() {
        Node[] nodes = new Node[m + 1];
        for (int i = 1; i <= m; i++) {
            Shark shark = sharks[i];
            if (shark.isOut) {
                continue;
            }
            nodes[i] = findPosition(i, shark);
        }
        return nodes;
    }

    public static Node findPosition(int sharkNumber, Shark shark) {
        int curDir = shark.dir;
        // 이동할 수 있는 위치 찾으면 즉시 반환
        for (int i = 0; i < 4; i++) {
            int move = sharkMoves[sharkNumber][curDir][i];
            int nextR = shark.r + rows[move];
            int nextC = shark.c + cols[move];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                continue;
            }
            if (map[nextR][nextC] == 0 && smellMap[nextR][nextC] == null) {
                return new Node(nextR, nextC, sharkMoves[sharkNumber][curDir][i]);
            }
        }
        // 아니라면 자신이 뿌린 냄새로 이동
        for (int i = 0; i < 4; i++) {
            int move = sharkMoves[sharkNumber][curDir][i];
            int nextR = shark.r + rows[move];
            int nextC = shark.c + cols[move];

            if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                continue;
            }
            if (smellMap[nextR][nextC] != null && smellMap[nextR][nextC].sharkNumber == sharkNumber) {
                return new Node(nextR, nextC, sharkMoves[sharkNumber][curDir][i]);
            }
        }
        return null;
    }
}