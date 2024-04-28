package boj;

import java.io.*;
import java.util.*;

class Boj13908 {

    static int n, m;
    static int providedNumber;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (m != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                providedNumber |= 1 << Integer.parseInt(st.nextToken());
            }
        }

        search(0, 0);

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int select) {
        if (index == n) {
            if ((select & providedNumber) == providedNumber) {
                count++;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            search(index + 1, select | (1 << i));
        }
    }
}
