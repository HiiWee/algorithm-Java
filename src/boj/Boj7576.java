package boj;

import java.io.*;
import java.util.*;

/**
 * 큐의 FIFO를 생각하지 않고 풀이를 시도한 것이 오래 걸렸던 이유가 됨.
 * 자료구조의 특성을 잘 생각하며 풀이를 하자..
 */
class Boj7576 {
    static int[][] arr;
    static int[] row = {1, 0, -1, 0};
    static int[] col = {0, 1, 0, -1};
    static int n, m;
    static int maxCount = 0;
    static int zeroCount = 0;
    static Queue<Integer> que;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        que = new LinkedList<>();
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                if (num == 0) {
                    zeroCount++;
                } else if (num == 1) {
                    que.add(i);
                    que.add(j);
                }
            }
        }
        BFS();
        // zeroCount--를 하는 과정에서 만약 모든 0을 탐색하지 못했다면 익지 않은 토마토가 존재함을 의미
        if (zeroCount != 0) {
            bw.write(-1 + "\n");
        }
        // 아무런 애초에 전부 익어있다면
        else if (maxCount == 0) {
            bw.write(0 + "\n");
        } else {
            bw.write(maxCount - 1 + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        while (!que.isEmpty()) {
            // 만약 2개의 초기 토마토가 존재하면 2개의 토마토가 번갈아가며 날짜가 업데이트 된다. (FIFO특성)
            int o1 = que.poll();
            int o2 = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = o1 + row[i];
                int nextC = o2 + col[i];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) {
                    continue;
                }
                if (arr[nextR][nextC] == 0) {
                    que.add(nextR);
                    que.add(nextC);
                    if (arr[nextR][nextC] == 0) {
                        zeroCount--;
                        // 직전 탐색한 토마토의 일수 + 1
                        arr[nextR][nextC] = arr[o1][o2] + 1;
                        maxCount = Math.max(arr[nextR][nextC], maxCount);
                    }
                }
            }
        }
    }
}