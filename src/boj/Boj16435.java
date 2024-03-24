package boj;

import java.io.*;
import java.util.*;

class Boj16435 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] feeds = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            feeds[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(feeds);

        for (int feed : feeds) {
            if (feed <= l) {
                l++;
                continue;
            }
            break;
        }

        bw.write(Integer.toString(l));
        bw.flush();
        bw.close();
    }
}