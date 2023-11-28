package boj;

import java.io.*;
import java.util.*;

class Boj15685 {

    private static final int[] rows = {0, -1, 0, 1};
    private static final int[] cols = {1, 0, -1, 0};
    private static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        map = new boolean[101][101];

        for (int i = 0; i < n; i++) {
            List<Integer> directions;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            directions = getDirections(d, g);
            move(y, x, directions);
        }

        int count = 0;
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }

        bw.write(count + "");
        bw.flush();
        bw.close();
    }

    public static void move(int r, int c, List<Integer> directions) {
        map[r][c] = true;
        for (int direction : directions) {
            r += rows[direction];
            c += cols[direction];
            map[r][c] = true;
        }
    }

    public static List<Integer> getDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        while (g-- > 0) {
            for (int i = directions.size() - 1; i >= 0; i--) {
                directions.add((directions.get(i) + 1) % 4);
            }
        }
        return directions;
    }
}