package boj;

import java.io.*;

class Boj2588 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        bw.write((a * (b % 10)) + "\n");
        bw.write((a * (b / 10 % 10)) + "\n");
        bw.write((a * (b / 100)) + "\n");
        bw.write(a * b + "\n");
        bw.flush();
        bw.close();
    }
}