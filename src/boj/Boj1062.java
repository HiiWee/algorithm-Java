package boj;

import java.io.*;
import java.util.*;

class Boj1062 {

    static int maxCount;
    static int n, k;
    static int[] words;
    static final int targetBit = 0b10000010000100000101;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if (k < 5) {
            System.out.println("0");
            return;
        }
        if (k == 26) {
            System.out.println(n);
            return;
        }
        words = new int[n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (char c : line.toCharArray()) {
                words[i] |= 1 << (c - 'a');
            }
        }

        combinations(0, 5, targetBit);

        bw.write(maxCount + "");
        bw.flush();
        bw.close();
    }

    public static void combinations(int index, int count, int bit) {
        if (count == k) {
            int curCount = 0;
            for (int word : words) {
                if ((word & bit) == word) {
                    curCount++;
                }
            }
            maxCount = Math.max(curCount, maxCount);
            return;
        }

        for (int i = index; i < 26; i++) {
            if ((bit & 1 << i) == 0) {
                combinations(i + 1, count + 1, bit | 1 << i);
            }
        }
    }
}