package boj;

import java.io.*;
import java.util.StringTokenizer;

class Boj1850 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long num;
        if (n > m) {
            num = GCD(n, m);
        } else {
            num = GCD(m, n);
        }

        for (long i = 0L; i < num; i++) {
            bw.write("1");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
    }

    public static long GCD(long a, long b) {
        long result = a % b;
        if (result == 0) {
            return b;
        }
        return GCD(b, result);
    }
}
