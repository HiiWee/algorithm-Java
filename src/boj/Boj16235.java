package boj;

/*
    - 양분은 초기에 모든칸에 5씩 들어있음

    봄 - 나무가 자신의 나이만큼 양분을 먹음 -> 이후 나이가 1증가함
        여러개의 나무가 있다면 나이가 어린 나무부터 먹는데 나무는 양분을 먹지 못하고 죽음

    여름 - 죽은 나무가 양분이 됨 -> 죽은 나무의 나이 / 2에 해당되는 양분이 죽은 칸에 추가됨
    가을 - 나무가 번식함 나무 나이가 5의 배수라면 인접8개의 칸에 나이가 1인 나무가 생김
    겨울 - s2d2가 돌아다니면서 땅에 양분을 추가함 A[R][C]

    위 4개의 과정이 1년 이를 K년 반복한다.
*/

import java.io.*;
import java.util.*;

class Boj16235 {

    private static final int[] rows = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static final int[] cols = {-1, 0, 1, 1, 1, 0, -1, -1};

    private static int n, m, k;
    private static int[][] a;
    private static int[][] map;
    private static Deque<Tree> aliveTrees = new LinkedList<>();
    private static Queue<Tree> deadTrees = new LinkedList<>();

    static class Tree {
        int r, c;
        int age;

        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        public boolean canGetOld(int feed) {
            return feed >= age;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        a = new int[n + 1][n + 1];
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                Arrays.fill(map[i], 5);
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            aliveTrees.offer(new Tree(r, c, age));
        }

        while (k-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        bw.write(aliveTrees.size() + "");
        bw.flush();
        bw.close();
    }

    public static void spring() {
        for (int i = 0; i < aliveTrees.size();) {
            Tree tree = aliveTrees.poll();
            int r = tree.r;
            int c = tree.c;
            // 나이를 먹으면 양분 제거
            if (tree.canGetOld(map[r][c])) {
                map[r][c] -= tree.age;
                tree.age++;
                aliveTrees.offer(tree);
                i++;
            } else {
                deadTrees.offer(tree);
            }
        }
    }

    public static void summer() {
        while (!deadTrees.isEmpty()) {
            Tree tree = deadTrees.poll();
            int r = tree.r;
            int c = tree.c;
            int feed = tree.age / 2;
            map[r][c] += feed;
        }
    }

    public static void fall() {
        Queue<Tree> tempQue = new LinkedList<>();
        for (Tree aliveTree : aliveTrees) {
            if (aliveTree.age % 5 == 0) {
                tempQue.offer(aliveTree);
            }
        }
        while (!tempQue.isEmpty()) {
            Tree tree = tempQue.poll();
            for (int i = 0; i < 8; i++) {
                int nextR = tree.r + rows[i];
                int nextC = tree.c + cols[i];

                if (nextR < 1 || nextR > n || nextC < 1 || nextC > n) {
                    continue;
                }
                aliveTrees.offerFirst(new Tree(nextR, nextC, 1));
            }
        }
    }

    public static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] += a[i][j];
            }
        }
    }
}
