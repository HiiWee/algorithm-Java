package boj;

import java.util.*;
import java.io.*;

class Boj23971 {

    static int h, w, n, m;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int hCount = h / (n + 1);
        int wCount = w / (m + 1);
        if (h % (n + 1) != 0) {
            hCount++;
        }
        if (w % (m + 1) != 0) {
            wCount++;
        }
        bw.write(Integer.toString(hCount * wCount));
        bw.flush();
        bw.close();
    }
}