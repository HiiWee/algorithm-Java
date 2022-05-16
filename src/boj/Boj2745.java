package boj;

import java.io.*;
import java.util.StringTokenizer;

class Boj2745 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String line = st.nextToken();
        int m = Integer.parseInt(st.nextToken());
        int decimal = 0;
        int length = line.length() - 1;
        for (int i = length; i >= 0; i--) {
            int c = line.charAt(i);
            if (c >= '0' && c <= '9') {
                decimal += (c - '0') * Math.pow(m, length - i);
            } else {
                decimal += (c - 'A' + 10) * Math.pow(m, length - i);
            }
        }
        bw.write(decimal + "\n");
        bw.flush();
        bw.close();
    }
}