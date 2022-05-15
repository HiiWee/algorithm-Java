package boj;

import java.io.*;
import java.util.StringTokenizer;

class Boj1934 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int num = GCD(a, b);
            bw.write(a * b / num + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static int GCD(int a, int b) {
        int result = a % b;
        if (result == 0) {
            return b;
        }
        return GCD(b, result);
    }
}