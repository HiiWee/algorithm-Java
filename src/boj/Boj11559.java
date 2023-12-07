package boj;

import java.io.*;
import java.util.*;

class Boj11559 {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int[] rows = {-1, 0, 1, 0};
    private static final int[] cols = {0, 1, 0, -1};

    private static final char[][] map = new char[12][6];
    private static final List<Node> bubbles = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int count = 0;
        while (bubblePop()) {
            count++;
            moveDown();
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    public static void moveDown() {
        for (int i = 0; i < 6; i++) {
            while (true) {
                // 일단 해당 열의 마지막 행부터 차례로 .을 찾기
                int firstDot = findFirstDot(i);
                if (firstDot == -1) {
                    break;
                }
                // . 이후에 심볼이 있다면 그 사이 공간은 터진 공간임
                int firstSymbolIndex = findFirstSymbolAfter(i, firstDot);
                if (firstSymbolIndex == -1) {
                    break;
                }
                // 첫번째 심볼이후 끝나는 자리를 찾음
                int secondSymbolIndex = findLastSymbolAfter(i, firstSymbolIndex);

                // 찾은 심볼을 firstDot위치부터 채워넣음
                moveBubbleDown(i, firstDot, firstSymbolIndex, secondSymbolIndex);
            }
        }
    }

    public static void moveBubbleDown(int col, int firstDot, int startInclusive, int endInclusive) {
        for (int i = startInclusive; i >= endInclusive; i--) {
            map[firstDot--][col] = map[i][col];
            map[i][col] = '.';
        }
    }

    public static int findLastSymbolAfter(int col, int index) {
        for (int i = index; i >= 0; i--) {
            if (map[i][col] == '.') {
                return i + 1;
            }
        }
        return 0;
    }

    public static int findFirstSymbolAfter(int col, int index) {
        for (int i = index; i >= 0; i--) {
            if (map[i][col] != '.') {
                return i;
            }
        }
        return -1;
    }

    public static int findFirstDot(int col) {
        for (int i = 11; i >= 0; i--) {
            if (map[i][col] == '.') {
                return i;
            }
        }
        return -1;
    }

    public static boolean bubblePop() {
        boolean isPop = false;
        boolean[][] visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (!visited[i][j] && map[i][j] != '.') {
                    isPop |= bfs(visited, new Node(i, j));
                }
            }
        }
        if (isPop) {
            removeBubbles();
            bubbles.clear();
        }
        return isPop;
    }

    public static void removeBubbles() {
        for (Node node : bubbles) {
            map[node.r][node.c] = '.';
        }
    }

    public static boolean bfs(boolean[][] visited, Node startNode) {
        List<Node> tempNodes = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();

        tempNodes.add(startNode);
        que.offer(startNode);
        visited[startNode.r][startNode.c] = true;
        char targetSymbol = map[startNode.r][startNode.c];

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = rows[i] + curNode.r;
                int nextC = cols[i] + curNode.c;

                if (checks(nextR, nextC)) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC] == targetSymbol) {
                    visited[nextR][nextC] = true;
                    Node nextNode = new Node(nextR, nextC);
                    tempNodes.add(nextNode);
                    que.offer(nextNode);
                }
            }
        }
        if (tempNodes.size() >= 4) {
            bubbles.addAll(tempNodes);
            return true;
        }
        return false;
    }

    public static boolean checks(int r, int c) {
        return r < 0 || r >= 12 || c < 0 || c >= 6;
    }
}