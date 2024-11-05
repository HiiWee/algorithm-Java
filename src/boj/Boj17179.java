package boj;

import java.io.*;
import java.util.*;

class Boj17179 {

    static int n, m, L;
    static int[] places;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        places = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            places[i] = Integer.parseInt(br.readLine());
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int target = Integer.parseInt(br.readLine());

            int l = 0;
            int r = 4_000_000;

            while (l < r) {
                int mid = (l + r) / 2;

                int count = 0;
                int preCut = 0;
                for (int j = 1; j <= m; j++) {
                    if (places[j] - preCut >= mid) {
                        count++;
                        preCut = places[j];
                    }
                }
                if (L - preCut < mid) {
                    count--;
                }

                if (count < target) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            answer.append(l - 1).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
