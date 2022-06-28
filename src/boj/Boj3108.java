package boj;

import java.io.*;
import java.util.*;

class Boj3108 {
    static int N;
    static Rect[] map;
    static boolean[] visited;
    static int count = 0;

    static class Rect {
        int x1, y1, x2, y2;

        public Rect(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new Rect[N + 1];
        visited = new boolean[N + 1];

        // 펜을 내리고 시작하므로 0,0에 인접한 사각형을 초기에 판별하기 위한 1순위 탐색 지점 추가
        map[0] = new Rect(0, 0, 0, 0);
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            map[i] = new Rect(x1, y1, x2, y2);
        }

        for (int i = 0; i <= N; i++) {
            BFS(i);
        }
        bw.write((count - 1) + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS(int index) {
        if (visited[index]) {
            return;
        }
        Queue<Integer> que = new LinkedList<>();
        que.add(index);
        visited[index] = true;

        while (!que.isEmpty()) {
            int next = que.poll();
            for (int i = 0; i <= N; i++) {
                if (visited[i] || i == next || !isConnected(next, i)) {
                    continue;
                }
                que.add(i);
                visited[i] = true;
            }
        }
        count++;
    }

    public static boolean isConnected(int index, int next) {
        Rect r1 = map[index];
        Rect r2 = map[next];

        // r1가 r2을 포함 하는 경우, r1 r2가 아예 떨어져 있는 경우, r2가 r1을 포함하는 경우면 false
        if ((r1.x1 < r2.x1 && r1.y1 < r2.y1 && r1.x2 > r2.x2 && r1.y2 > r2.y2)
                || (r2.x1 < r1.x1 && r2.y1 < r1.y1 && r2.x2 > r1.x2 && r2.y2 > r1.y2)
                || (r1.x1 > r2.x2 || r1.x2 < r2.x1 || r1.y1 > r2.y2 || r1.y2 < r2.y1)) {
            return false;
        }
        return true;
    }
}