package boj;

import java.io.*;
import java.util.*;

class Boj19939 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int sum = (k * (k + 1)) / 2;
        if (sum > n) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }

        n -= sum;
        n %= k;

        if (n == 0) {
            bw.write(Integer.toString(k - 1));
        } else {
            bw.write(Integer.toString(k));
        }
        bw.flush();
        bw.close();
    }
}
