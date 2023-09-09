package boj;

import java.io.*;
import java.util.*;

class Boj22858 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] d = new int[n];
        int[] s = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st2.nextToken());
        }

        while (k-- > 0) {
            for (int i = 0; i < n; i++) {
                temp[d[i] - 1] = s[i];
            }
            for (int i = 0; i < n; i++) {
                s[i] = temp[i];
            }
        }

        StringBuilder result = new StringBuilder();
        for (int number : s) {
            result.append(number).append(" ");
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }
}