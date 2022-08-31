package boj;

import java.io.*;

class Boj1157 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine().toUpperCase();
        int[] arr = new int[26];
        int max = 0;
        for (int i = 0; i < line.length(); i++) {
            int index = line.charAt(i) - 'A';
            arr[index]++;
            max = Math.max(arr[index], max);
        }

        int index = -1;
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] == max) {
                index = i;
                count++;
            }
        }

        if (count > 1) {
            bw.write("?\n");
        } else {
            bw.write((char)(index + 'A') + "\n");
        }
        bw.flush();
        bw.close();
    }
}