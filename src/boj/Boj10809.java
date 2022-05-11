package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj10809 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        int[] count = new int[26];

        for (int i = 0; i < 26; i++) {
            count[i] = -1;
        }
        for (int i = 0; i < line.length(); i++) {
            int index = line.charAt(i) - 'a';
            if (count[index] == -1) {
                count[index] = i;
            }
        }
        for (int i = 0; i < 26; i++) {
            sb.append(count[i]).append(" ");
        }

        System.out.println(sb);
    }
}
