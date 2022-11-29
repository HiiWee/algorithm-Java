package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Boj9625 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dpA = new int[n + 1];
        int[] dpB = new int[n + 1];

        dpA[0] = 1;
        dpA[1] = 0;
        dpB[0] = 0;
        dpB[1] = 1;
        for (int i = 2; i <= n; i++) {
            dpA[i] = dpA[i - 1] + dpA[i - 2];
            dpB[i] = dpB[i - 1] + dpB[i - 2];
        }
        bw.write(dpA[n] + " " + dpB[n]);
        bw.flush();
        bw.close();
    }
}
