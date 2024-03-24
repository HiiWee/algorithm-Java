package boj;

import java.io.*;
import java.util.*;

class Boj1817 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (n == 0) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }
        int[] books = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            books[i] = Integer.parseInt(st.nextToken());
        }

        int boxCount = 1;
        int capacity = m;
        for (int book : books) {
            if (capacity >= book) {
                capacity -= book;
            } else {
                boxCount++;
                capacity = m - book;
            }
        }

        bw.write(Integer.toString(boxCount));
        bw.flush();
        bw.close();
    }
}