package boj;

import java.io.*;

class Boj16719 {
    private static boolean[] visited;
    private static StringBuilder result = new StringBuilder();
    private static String line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        line = br.readLine();
        visited = new boolean[line.length()];

        zoac(0, line.length() - 1);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void zoac(int left, int right) {
        if (left > right) {
            return;
        }
        int idx = left;

        for (int i = left; i <= right; i++) {
            if (line.charAt(idx) > line.charAt(i)) {
                idx = i;
            }
        }
        visited[idx] = true;
        for (int i = 0; i < line.length(); i++) {
            if (visited[i]) {
                result.append(line.charAt(i));
            }
        }
        result.append("\n");

        zoac(idx + 1, right);
        zoac(left, idx - 1);
    }
}