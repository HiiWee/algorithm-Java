package boj;

/*
    0초부터 시작한다고 했을때
        - 0 ~ 1초동안 아무것도 안함
        - 1 ~ 2초동안 폭탄이 없는 곳에 폭탄을 설치함
        - 3 ~ 4초동안 초기 폭탄 터짐
        - 4 ~ 5초동안 폭탄이 없는 곳에 폭탄을 설치 -> 반복잡업 N초가 될 때 까지
*/
import java.io.*;
import java.util.*;

class Boj16918 {

    private static int[] rows = {-1, 0, 1, 0};
    private static int[] cols = {0, 1, 0, -1};

    private static int r, c;
    private static char[][] map;
    private static int[][] countMap;

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        countMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            Arrays.fill(countMap[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'O') {
                    countMap[i][j] = 0;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                fillBomb(i);
                continue;
            }
            List<Node> nodes = new ArrayList<>();
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (i - countMap[j][k] >= 3) {
                        nodes.add(new Node(j, k));
                    }
                }
            }
            explodeBomb(nodes);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void explodeBomb(List<Node> nodes) {
        for (Node node : nodes) {
            countMap[node.r][node.c] = Integer.MAX_VALUE;
            map[node.r][node.c] = '.';

            for (int i = 0; i < 4; i++) {
                int nextR = node.r + rows[i];
                int nextC = node.c + cols[i];

                if (nextR < 0 || nextR >= r || nextC < 0 || nextC >= c) {
                    continue;
                }
                map[nextR][nextC] = '.';
                countMap[nextR][nextC] = Integer.MAX_VALUE;
            }
        }
    }

    public static void fillBomb(int second) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (countMap[i][j] == Integer.MAX_VALUE) {
                    countMap[i][j] = second;
                    map[i][j] = 'O';
                }
            }
        }
    }
}