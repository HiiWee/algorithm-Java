package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj11655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringBuilder encryptLine = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                if (c > 'M') {
                    encryptLine.append((char)(c + 13 - 26));
                } else {
                    encryptLine.append((char)(c + 13));
                }
            } else if (c >= 'a' && c <= 'z') {
                if (c > 'm') {
                    encryptLine.append((char)(c + 13 - 26));
                } else {
                    encryptLine.append((char)(c + 13));
                }
            } else {
                encryptLine.append(c);
            }
        }
        System.out.println(encryptLine);
    }
}

