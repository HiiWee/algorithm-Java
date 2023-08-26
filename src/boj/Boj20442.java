package boj;

import java.io.*;

class Boj20442 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();

        int rCount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'R') {
                rCount++;
            }
        }
        int[] leftK = new int[rCount];
        int[] rightK = new int[rCount];

        int index = 0;
        int kCount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'K') {
                kCount++;
            } else {
                leftK[index++] = kCount;
            }
        }
        kCount = 0;
        for (int i = line.length() - 1; i >= 0; i--) {
            if (line.charAt(i) == 'K') {
                kCount++;
            } else {
                rightK[--index] = kCount;
            }
        }

        int maxLength = 0;
        int l = 0;
        int r = rCount - 1;

        while (l <= r) {
            maxLength = Math.max(maxLength, r - l + 1 + Math.min(leftK[l], rightK[r]) * 2);

            if (leftK[l] > rightK[r]) {
                r--;
            } else {
                l++;
            }
        }

        bw.write(maxLength + "");
        bw.flush();
        bw.close();
    }
}
