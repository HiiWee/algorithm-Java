package boj;

import java.io.*;

class Boj2089 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        if (num == 0) {
            bw.write(0 + "\n");
            bw.flush();
            bw.close();
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 1) {
            sb.append(Math.abs(num % -2));
            num = (int) Math.ceil((double)num / -2);
        }
        sb.append(num);
        sb.reverse();
        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
    }
}