package boj;

import java.util.*;
import java.io.*;

class Boj5014 {
    static int[] floors;
    static boolean[] visited;
    static int[] count;
    static int[] move = new int[2];
    // F: 전체층, S: 현재층, G: 목표층, U:올라갈수있는 층, D: 내려갈 수 있는 층
    static int F, S, G, U, D;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        floors = new int[F + 1];
        visited = new boolean[F + 1];
        count = new int[F + 1];
        move[0] = U;
        // 내려가는 층은 음의 정수로 변환
        move[1] = -D;

        if (BFS(S)) {
            bw.write(count[G] + "\n");
        } else {
            bw.write("use the stairs\n");
        }
        bw.flush();
        bw.close();
    }

    public static boolean BFS(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;

        // 현재층과 목표층이 동일한 경우 0번 누름
        if (start == G) {
            return true;
        }
        while (!que.isEmpty()) {
            int floor = que.poll();

            for (int i = 0; i < 2; i++) {
                int nextFloor = floor + move[i];

                if (nextFloor == G) {
                    count[nextFloor] = count[floor] + 1;
                    return true;
                }
                if (nextFloor < 1 || nextFloor > F) {
                    continue;
                }

                if (!visited[nextFloor]) {
                    que.add(nextFloor);
                    visited[nextFloor] = true;
                    count[nextFloor] = count[floor] + 1;
                }
            }
        }
        return false;
    }
}