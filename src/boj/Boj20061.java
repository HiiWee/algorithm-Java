package boj;

import java.io.*;
import java.util.*;

class Boj20061 {

    static class Node {
        int r, c;

        Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean[][] blueMap = new boolean[4][6];
    static boolean[][] greenMap = new boolean[6][4];
    static int score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 모든 블록을 가져오고
            Node[] nodes = getBlock(t, x, y);
            // 파란 보드에 채움
            fillBlueMap(t, nodes);
            // 빨간 보드에 채움
            fillGreenMap(t, nodes);
            // 점수를 계산함
            calculateBlueMap();
            calculateGreenMap();

            // 연한 색상에 있는경우 칸의 수만큼 삭제해야함
            deleteBrightBlueMap();
            deleteBrightGreenMap();

        }
        int count = 0;
        for (int i = 5; i >= 2; i--) {
            for (int j = 0; j <= 3; j++) {
                if (blueMap[j][i]) {
                    count++;
                }
                if (greenMap[i][j]) {
                    count++;
                }
            }
        }

        bw.write(score + "\n" + count);
        bw.flush();
        bw.close();
    }

    public static void deleteBrightGreenMap() {
        int count = 0;
        for (int r = 0; r <= 1; r++) {
            for (int c = 0; c <= 3; c++) {
                // 밝은 영역에 하나의 열이라도 차지하고 있다면 오른쪽으로 당겨주어야 함
                if (greenMap[r][c]) {
                    count++;
                    break;
                }
            }
        }
        // 차지한 행의 개수만큼 아래로 당겨주어야 함
        while (count-- > 0) {
            pullDown(5);
        }
    }

    public static void deleteBrightBlueMap() {
        int count = 0;
        for (int c = 0; c <= 1; c++) {
            for (int r = 0; r <= 3; r++) {
                // 밝은 영역에 하나의 열이라도 차지하고 있다면 오른쪽으로 당겨주어야 함
                if (blueMap[r][c]) {
                    count++;
                    break;
                }
            }
        }
        // 차지한 열의 개수만큼 우측 끝에서 당겨주어야 함
        while (count-- > 0) {
            pullRight(5);
        }
    }

    public static void calculateGreenMap() {
        for (int r = 5; r >= 0; r--) {
            int count = 0;
            for (int c = 0; c <= 3; c++) {
                if (greenMap[r][c]) {
                    count++;
                }
            }
            if (count == 4) {
                score++;
                popGreenMap(r);
                pullDown(r);
                r++;
            }
        }
    }

    public static void pullDown(int row) {
        for (int r = row; r >= 0; r--) {
            for (int c = 0; c <= 3; c++) {
                // 당겨올때 0열이면 false으로 채워야 함
                if (r == 0) {
                    greenMap[r][c] = false;
                } else {
                    // 한칸씩 당겨오기
                    greenMap[r][c] = greenMap[r - 1][c];
                }
            }
        }
    }

    public static void popGreenMap(int row) {
        for (int i = 0; i <= 3; i++) {
            greenMap[row][i] = true;
        }
    }

    public static void calculateBlueMap() {
        for (int c = 5; c >= 0; c--) {
            int count = 0;
            for (int r = 0; r <= 3; r++) {
                if (blueMap[r][c]) {
                    count++;
                }
            }
            if (count == 4) {
                score++;
                popBlueMap(c);
                pullRight(c);
                c++; // 한칸씩 당겼으므로 현재위치 재 탐색
            }
        }
    }

    public static void pullRight(int col) {
        for (int c = col; c >= 0; c--) {
            for (int r = 0; r <= 3; r++) {
                // 당겨올때 0열이면 false으로 채워야 함
                if (c == 0) {
                    blueMap[r][c] = false;
                } else {
                    // 한칸씩 당겨오기
                    blueMap[r][c] = blueMap[r][c - 1];
                }
            }

        }
    }

    public static void popBlueMap(int col) {
        for (int i = 0; i <= 3; i++) {
            blueMap[i][col] = false;
        }

    }

    public static void fillGreenMap(int t, Node[] nodes) {
        for (int r = 5; r >= 0; r--) {
            if (canFillGreenMap(r, t, nodes)) {
                fillGreen(r, t, nodes);
                break;
            }
        }
    }

    public static boolean canFillGreenMap(int row, int t, Node[] nodes) {
        for (int r = row; r >= 0; r--) {
            if (t == 1) {
                // 채우려는 공간에 하나라도 차있으면
                if (greenMap[r][nodes[0].c]) {
                    return false;
                }
            } else if (t == 2) {
                if (greenMap[r][nodes[0].c] || greenMap[r][nodes[1].c]) {
                    return false;
                }
            } else if (t == 3 && r > 0){
                if (greenMap[r][nodes[0].c] || greenMap[r - 1][nodes[0].c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void fillGreen(int row, int t, Node[] nodes) {
        // 1 X 1
        if (t == 1) {
            greenMap[row][nodes[0].c] = true;
        } else if (t == 2) { // 1 X 2
            greenMap[row][nodes[0].c] = true;
            greenMap[row][nodes[1].c] = true;
        } else {
            greenMap[row][nodes[0].c] = true;
            greenMap[row - 1][nodes[0].c] = true;
        }
    }

    public static void fillBlueMap(int t, Node[] nodes) {
        for (int c = 5; c >= 0; c--) {
            if (canFillBlueMap(c, t, nodes)) {
                fillBlue(c, t, nodes);
                break;
            }
        }
    }

    public static boolean canFillBlueMap(int col, int t, Node[] nodes) {
        for (int c = col; c >= 0; c--) {
            if (t == 1) {
                // 채우려는 공간에 하나라도 차있으면
                if (blueMap[nodes[0].r][c]) {
                    return false;
                }
            } else if (t == 2 && c > 0) {
                if (blueMap[nodes[1].r][c] || blueMap[nodes[1].r][c - 1]) {
                    return false;
                }
            } else {
                if (blueMap[nodes[0].r][c] || blueMap[nodes[1].r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void fillBlue(int col, int t, Node[] nodes) {
        // 1 X 1 블록을 채울때
        if (t == 1) {
            blueMap[nodes[0].r][col] = true;
        } else if (t == 2) { // 1 X 2
            blueMap[nodes[1].r][col] = true;
            blueMap[nodes[1].r][col - 1] = true;
        } else { // 2 X 1
            blueMap[nodes[0].r][col] = true;
            blueMap[nodes[1].r][col] = true;
        }
    }

    public static Node[] getBlock(int t, int x, int y) {
        if (t == 1) {
            return new Node[]{new Node(x, y)};
        }
        if (t == 2) {
            return new Node[]{new Node(x, y), new Node(x, y + 1)};
        }
        return new Node[]{new Node(x, y), new Node(x + 1, y)};
    }
}