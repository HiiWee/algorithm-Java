package boj;

import java.io.*;
import java.util.*;

class Boj1497 {
    static int n, m;
    static long[] guitars;
    static int max = Integer.MIN_VALUE;
    static int guitarCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        guitars = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            String line = st.nextToken();
            long guitar = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'Y') {
                    guitar |= (1L << j);
                }
            }
            guitars[i] = guitar;
        }

        search(0, 0, 0);

        if (guitarCount == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(guitarCount));
        }
        bw.flush();
        bw.close();
    }

    public static void search(int index, int count, long sum) {
        if (index == n) {
            int possibleCount = Long.bitCount(sum);

            if (possibleCount == 0) {
                return;
            }
            if (possibleCount >= max) {
                max = possibleCount;
                guitarCount = Math.min(count, guitarCount);
            }
            return;
        }

        search(index + 1, count + 1, sum | guitars[index]);
        search(index + 1, count, sum);
    }
}

