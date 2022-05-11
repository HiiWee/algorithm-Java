package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj10820 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // 소문자, 대문자, 숫자, 공백
        int[] count = new int[4];
        while ((line = br.readLine()) != null) {
            for (int i = 0 ; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    count[0]++;
                } else if (c >= 'A' && c <= 'Z') {
                    count[1]++;
                } else if (c >= '0' && c <= '9') {
                    count[2]++;
                } else {
                    count[3]++;
                }
            }
            for (int i = 0; i < 4; i++) {
                System.out.print(count[i] + " ");
                count[i] = 0;
            }
            System.out.println();
        }
    }
}
