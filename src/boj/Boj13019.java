package boj;

import java.io.*;
import java.util.*;

class Boj13019 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int answer = 0;
        int bIndex = b.length - 1;

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == b[bIndex]) {
                bIndex--;
            } else {
                answer++;
            }
        }

        Arrays.sort(a);
        Arrays.sort(b);
        if (new String(a).equals(new String(b))) {
            bw.write(Integer.toString(answer));
        } else {
            bw.write("-1");
        }
        bw.flush();
        bw.close();
    }
}

