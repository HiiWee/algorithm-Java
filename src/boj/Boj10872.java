package boj;

import java.io.*;

class Boj10872 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        if (n == 0) {
            bw.write(1 + "\n");
            bw.flush();
            bw.close();
            return;
        }
        bw.write(factorial(n) + "\n");
        bw.flush();
        bw.close();
    }

    public static long factorial(int n) {
        if (n == 1) {
            return 1L;
        }
        return factorial(n - 1) * n;
    }
}