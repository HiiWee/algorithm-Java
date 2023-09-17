package boj;

import java.io.*;
import java.util.*;

class Boj20207 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] days = new int[367];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                days[j]++;
            }
        }

        int result = 0;
        int width = 0;
        int height = 0;
        for (int i = 1; i <= 366; i++) {
            if (days[i] > 0) {
                height++;
                width = Math.max(days[i], width);
            } else if (days[i] == 0) {
                result += height * width;
                height = 0;
                width = 0;
            }
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}
