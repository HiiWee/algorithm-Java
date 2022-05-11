package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj2743 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line.length());
        }
    }
}

