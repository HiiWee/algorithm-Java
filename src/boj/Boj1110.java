package boj;

/*
    1. 26: 2 + 6 = 8 -> 68: 6 + 8 = 14
*/

import java.io.*;

class Boj1110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int count = 1;
        int num = (n % 10 * 10) + (n / 10 + n % 10) % 10;
        while (true) {
            if (num == n) {
                break;
            }
            num = (num % 10 * 10) + (num / 10 + num % 10) % 10;
            count++;
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}