package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj10808 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int[] count = new int[26];

        for (int i = 0; i < line.length(); i++) {
            count[line.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            System.out.print(count[i] + " ");
        }
    }
}