package boj;


import java.io.*;

class Boj10994 {
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int side = n + (n - 1) * 3;
        map = new char[side + 1][side + 1];

        recur(1, n, side / 2 + 1);

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= side; i++) {
            for (int j = 1; j <= side; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void recur(int side, int currentN, int goal) {
        if (side == goal) {
            map[side][side] = '*';
            return;
        }
        int limit = side + currentN + (currentN - 1) * 3;
        for (int i = side; i < limit; i++) {
            for (int j = side; j < limit; j++) {
                if (i == side) {
                    map[i][j] = '*';
                } else if (j == side) {
                    map[i][j] = '*';
                } else if (i == limit - 1) {
                    map[i][j] = '*';
                } else if (j == limit - 1) {
                    map[i][j] = '*';
                } else {
                    map[i][j] = ' ';
                }
            }
        }
        recur(side + 2, --currentN, goal);
    }
}