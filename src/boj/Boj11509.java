package boj;

import java.io.*;
import java.util.*;

class Boj11509 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[1_000_002];

        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            numbers[number]++;
            if (numbers[number + 1] > 0) {
                numbers[number + 1]--;
            } else {
                count++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}
