package boj;

import java.io.*;
import java.util.*;

class Boj20159 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] me = new long[n / 2];
        long[] other = new long[n / 2];

        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) {
                me[i / 2] = number;
                if (i / 2 > 0) {
                    me[i / 2] += me[i / 2 - 1];
                }
            } else {
                other[i / 2] = number;
                if (i / 2 > 0) {
                    other[i / 2] += other[i / 2 - 1];
                }
            }
        }
        if (n == 2) {
            bw.write(Long.toString(Math.max(me[0], other[0])));
            bw.flush();
            bw.close();
            return;
        }

        long max = Math.max(me[n / 2 - 1], other[n / 2 - 1]); // 밑장 안뻈을때 vs 내걸 첫번째 밑장 빼기
        max = Math.max(me[0] + other[n / 2 - 2], max);

        for (int i = 1; i < n / 2; i++) {
            max = Math.max(max, me[i - 1] + other[n / 2 - 1] - other[i - 1]); // 내걸 밑장빼기
            max = Math.max(max, me[i] + other[n / 2 - 2] - other[i - 1]); // 상대방을 밑장빼기
        }

        bw.write(Long.toString(max));
        bw.flush();
        bw.close();
    }
}
