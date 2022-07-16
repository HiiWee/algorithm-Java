package boj;

import java.io.*;
import java.util.*;

class Boj1758 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Long[] tip = new Long[n];
        for (int i = 0; i < n; i++) {
            tip[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(tip, Collections.reverseOrder());

        long total = 0;
        for (int i = 0; i < n; i++) {
            long num = tip[i] - ((i + 1) - 1);
            if (num > 0) {
                total += tip[i] - ((i + 1) - 1);
            }
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}