package boj;

/*
    2차원 배열의 토마토와 크게 다를것이 없다.
    우선 3차원으로 생각했을때 추가되는 움직임은 위에층으로 가는것과 아래층으로 가는것이다. 이때 행, 열의 값은 변함이 없다.

    각 배열을 입력받으면서 토마토(1)인 경우의 좌표는 모두 Node클래스로 감싸서 que에 담아 놓는다
    또한 0인 경우의 zeroCount를 모두 카운팅해놓는다.
    만약 카운팅한 zeroCount의 수가 0이라면 모든 토마토는 익어있는 상태이므로 0을 출력하고 종료한다.
    그렇지 않다면 BFS를 돌면서 초기 토마토의 위치들을 한번씩 돌아가면서
    주변의 숙성되지 않은 토마토(0)들을 숙성시키고 zeroCount도 하나씩 감소시킨다.
    또한, day카운트를 위해 초기노드의 값 + 1을 탐색 노드에 담으며 점차 범위를 넓히다가
    더이상 탐색할 수 없는 경우 BFS를 종료한다.

    BFS가 종료되면 먼저 zeroCount를 검사해야 한다 만약 zeroCount가 0이 아니라면
    어떤 방법을써도 숙성되지 않는 토마토가 존재하므로 -1을 출력하고

    zeroCount가 0이라면 day값을 1을 감소하고 출력하게 되면
    토마토가 숙성되는 최종 일 수를 구할 수 있다.
    (여기서 1을 감소하는 이유는 초기 토마토가 1이므로 날짜로 계산하기 위해선 1을 감소시켜주어야 한다.)
*/

import java.io.*;
import java.util.*;

class Boj7569 {
    static int H, N, M;
    static int[][][] map;
    static int zeroCount;
    static Queue<Node> que = new LinkedList<>();
    static int[] row = {-1, 0, 1, 0, 0, 0};
    static int[] col = {0, 1, 0, -1, 0, 0};
    static int[] height = {0, 0, 0, 0, 1, -1};
    static int day;

    static class Node {
        int r, c, h;

        Node(int h, int r, int c) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j][k] = num;
                    if (num == 0) {
                        zeroCount++;
                    } else if (num == 1) {
                        que.add(new Node(i, j, k));
                    }
                }
            }
        }
        if (zeroCount == 0) {
            bw.write("0\n");
            bw.flush();
            bw.close();
            return;
        }

        BFS();


        if (zeroCount != 0) {
            day = -1;
        } else {
            day--;
        }

        bw.write(day + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        while (!que.isEmpty()) {
            Node curNode = que.poll();
            for (int j = 0; j < 6; j++) {
                int nextH = curNode.h + height[j];
                int nextR = curNode.r + row[j];
                int nextC = curNode.c + col[j];

                if (nextH < 0 || nextH >= H || nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                if (map[nextH][nextR][nextC] == 0) {
                    map[nextH][nextR][nextC] = map[curNode.h][curNode.r][curNode.c] + 1;
                    day = Math.max(day, map[nextH][nextR][nextC]);
                    que.add(new Node(nextH, nextR, nextC));
                    zeroCount--;
                }

            }

        }
    }
}