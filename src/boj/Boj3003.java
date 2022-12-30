package boj;

import java.io.*;
import java.util.*;

class Boj3003 {
    static final int[] sequences = {1, 1, 2, 2, 2, 8};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int sequence : sequences) {
            bw.write((sequence - Integer.parseInt(st.nextToken())) + " ");
        }
        bw.flush();
        bw.close();
    }
}
