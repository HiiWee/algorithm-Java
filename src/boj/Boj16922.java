package boj;

import java.io.*;
import java.util.*;

class Boj16922 {

    static int n;
    static int[] number = {1, 5, 10, 50};
    static boolean[] visited = new boolean[1001];
    static int diffCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        dupCombinations(0, 0, 0);

        bw.write(Integer.toString(diffCount));
        bw.flush();
        bw.close();
    }

    public static void dupCombinations(int count, int sum, int index) {
        if (count == n) {
            if (!visited[sum]) {
                visited[sum] = true;
                diffCount++;
            }
            return;
        }

        for (int i = index; i < 4; i++) {
            dupCombinations(count + 1, sum + number[i], i);
        }
    }
}
