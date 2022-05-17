package boj;

import java.io.*;
import java.util.StringTokenizer;

class Boj11005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int quotient;
        int remainder;
        while (true) {
            quotient = n / m;
            remainder = n % m;

            if (remainder < 10) {
                sb.insert(0, remainder);
            } else {
                sb.insert(0, (char)(remainder - 10 + 'A'));
            }

            if (quotient == 0) {
                break;
            }
            n = quotient;
        }
        sb.append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}