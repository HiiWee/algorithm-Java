package boj;

import java.io.*;

class Boj1212 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String octal = br.readLine();
        String[] binary = {"000", "001", "010", "011", "100", "101", "110", "111", "", "1", "10", "11"};
        StringBuilder sb = new StringBuilder();

        if (octal.equals("0")) {
            bw.write(0 + "\n");
            bw.flush();
            bw.close();
            return;
        }
        for (int i = 0; i < octal.length(); i++) {
            char c = octal.charAt(i);
            if (i == 0 && c - '0' < 4) {
                sb.append(binary[c - '0' + 8]);
            } else {
                sb.append(binary[c - '0']);
            }
        }
        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
    }
}