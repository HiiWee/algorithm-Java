package boj;

import java.io.*;
import java.util.*;

class Boj20056 {

    private static final int[] rows = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] cols = {0, 1, 1, 1, 0, -1, -1, -1};

    private static int n, m, k;
    private static List<Node>[][] moveResults;
    private static List<Node> fireBalls = new ArrayList<>();

    static class Node {
        int r, c, m, s, d; // 행, 열, 질량, 속도, 방향

        Node (int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public void move() {
            r = (r + rows[d] * s + n * 1000) % n;
            c = (c + cols[d] * s + n * 1000) % n;
            moveResults[r][c].add(this);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        moveResults = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moveResults[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            fireBalls.add(new Node(
                    r,
                    c,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        while (k-- > 0) {
            moveFireBalls();
            splitFireBalls();
        }

        int sum = 0;
        for (Node fireBall : fireBalls) {
            sum += fireBall.m;
        }
        bw.write(sum + "");
        bw.flush();
        bw.close();
    }

    public static void moveFireBalls() {
        for (Node fireBall : fireBalls) {
            fireBall.move();
        }
    }

    public static void splitFireBalls() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (moveResults[i][j].size() > 1) {
                    sumFireBalls(i, j, moveResults[i][j]);
                    moveResults[i][j].clear();
                } else {
                    moveResults[i][j].clear();
                }
            }
        }
    }

    public static void sumFireBalls(int r, int c, List<Node> moveFireBalls) {
        int newM = 0;
        int newS = 0;
        boolean hasOdd = false;
        boolean hasEven = false;
        for (Node fireBall : moveFireBalls) {
            newM += fireBall.m;
            newS += fireBall.s;
            hasOdd |= fireBall.d % 2 == 1;
            hasEven |= fireBall.d % 2 == 0;
            fireBalls.remove(fireBall);
        }
        newM /= 5;
        newS /= moveFireBalls.size();
        if (newM == 0) {
            return;
        }
        // 홀수, 짝수 모두 존재
        if (hasOdd && hasEven) {
            split(new int[]{1, 3, 5, 7}, r, c, newM, newS);
        } else {
            split(new int[]{0, 2, 4, 6}, r, c, newM, newS);
        }
    }

    public static void split(int[] positions, int r, int c, int newM, int newS) {
        for (int position : positions) {
            Node newFireBall = new Node(r, c, newM, newS, position);
            fireBalls.add(newFireBall);
        }
    }
}