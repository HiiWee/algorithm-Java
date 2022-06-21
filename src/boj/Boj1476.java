package boj;

import java.util.*;
import java.io.*;

class Boj1476 {
    static final int E = 15;
    static final int S = 28;
    static final int M = 19;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int e_ = 1, s_ = 1, m_ = 1;
        int count = 1;
        while (e_ != e || s_ != s || m_ != m) {

            e_++;
            s_++;
            m_++;
            count++;

            if (e_ > E) {
                e_ = 1;
            }
            if (s_ > S) {
                s_ = 1;
            }
            if (m_ > M) {
                m_ = 1;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}