package boj;

import java.io.*;

class Boj1248 {

    static int n;
    static String line;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        line = br.readLine();
        map = new char[n][n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                map[i][j] = line.charAt(index++);
            }
        }

        search(0, new int[n]);
    }

    public static void search(int index, int[] out) {
        if (index == n) {
            StringBuilder answer = new StringBuilder();
            for (int number : out) {
                answer.append(number).append(" ");
            }
            System.out.println(answer);
            System.exit(0);
        }

        for (int i = -10; i <= 10; i++) {
            out[index] = i;
            if (isPossible(out, index)) {
                search(index + 1, out);
            }
        }
    }

    public static boolean isPossible(int[] out, int index) {
        int sum = 0;
        for (int i = index; i >= 0; i--) {
            sum += out[i];

            if (map[i][index] == '0' && sum != 0) {
                return false;
            }
            if (map[i][index] == '+' && sum <= 0) {
                return false;
            }
            if (map[i][index] == '-' && sum >= 0) {
                return false;
            }
        }

        return true;
    }
}
