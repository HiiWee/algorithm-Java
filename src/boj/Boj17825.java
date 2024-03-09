package boj;

import java.io.*;
import java.util.*;

class Boj17825 {

    static class Node {
        int number;
        int score;
        Node next;
        Node blueNext;

        public Node(int number, int score) {
            this.number = number;
            this.score = score;
        }
    }

    static class GameMap {
        Node[] nodes = new Node[33];

        public GameMap() {
            initMap();
        }

        public void initMap() {
            // 시작과 끝
            nodes[0] = new Node(0, 0);
            nodes[32] = new Node(32, 0);
            for (int i = 1; i <= 20; i++) {
                nodes[i] = new Node(i, i * 2);
                nodes[i - 1].next = nodes[i];
            }
            nodes[21] = new Node(21, 13);
            nodes[22] = new Node(22, 16);
            nodes[23] = new Node(23, 19);
            nodes[24] = new Node(24, 22);
            nodes[25] = new Node(25, 24);
            nodes[26] = new Node(26, 28);
            nodes[27] = new Node(27, 27);
            nodes[28] = new Node(28, 26);
            nodes[29] = new Node(29, 25); // 센터
            nodes[30] = new Node(30, 30);
            nodes[31] = new Node(31, 35);

            nodes[5].blueNext = nodes[21];
            nodes[21].next = nodes[22];
            nodes[22].next = nodes[23];
            nodes[23].next = nodes[29];
            nodes[10].blueNext = nodes[24];
            nodes[24].next = nodes[25];
            nodes[25].next = nodes[29];
            nodes[15].blueNext = nodes[26];
            nodes[26].next = nodes[27];
            nodes[27].next = nodes[28];
            nodes[28].next = nodes[29];
            nodes[29].next = nodes[30];
            nodes[30].next = nodes[31];
            nodes[31].next = nodes[20];
            nodes[20].next = nodes[32];
        }
    }

    static int[] counts = new int[10];
    static int[] positions = new int[33];
    static GameMap gameMap = new GameMap();
    static int maxScore = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            counts[i] = Integer.parseInt(st.nextToken());
        }
        search(0, new int[4], 0);

        bw.write(Integer.toString(maxScore));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] players, int score) {
        if (index == 10) {
            maxScore = Math.max(score, maxScore);
            return;
        }

        int moveCount = counts[index];
        for (int i = 0; i < 4; i++) {
            if (players[i] != 32) {
                Node destination = move(gameMap.nodes[players[i]], moveCount, 0);
                int preNumber = players[i];
                if (destination.number != 32 && positions[destination.number] > 0) {
                    continue;
                }
                players[i] = destination.number;
                positions[preNumber] = 0;
                positions[destination.number] = 1;
                search(index + 1, players, score + destination.score);
                positions[preNumber] = 1;
                positions[destination.number] = 0;
                players[i] = preNumber;
            }
        }
    }

    public static Node move(Node from, int moveCount, int depth) {
        // 끝에 도착하거나 도착지에 도착하면 반환
        if (from.next == null || moveCount == 0) {
            return from;
        }
        // 파란길을 우선적으로감 다만, 움직임의 첫 시작점이 파란길이 포함됐을떄만
        if (depth == 0 && from.blueNext != null) {
            return move(from.blueNext, moveCount - 1, depth + 1);
        }
        return move(from.next, moveCount - 1, depth + 1);
    }
}