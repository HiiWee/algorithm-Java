package boj;

import java.io.*;
import java.util.*;

// 이분탐색 Lower_bound, Uppder_bound 개념 잘 익혀두자
class Boj1654_1 {
    static int[] line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        line = new int[n];
        long high = 0;
        for (int i = 0; i < k; i++) {
            line[i] = Integer.parseInt(br.readLine());
            high = Math.max(line[i], high);
        }

        long low = 1;
        long mid;
        while (low <= high) {
            int count = 0;
            mid = (low + high) / 2;
            for (int i = 0; i < k; i++) {
                count += line[i] / mid;
            }
            if (count < n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }
        bw.write(high + "\n");
        bw.flush();
        bw.close();
    }
}

class Boj1654_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        long max = 0;
        int[] line = new int[k];
        for (int i = 0; i < k; i++) {
            line[i] = Integer.parseInt(br.readLine());
            max = Math.max(line[i], max);
        }

        long low = 1;
        long high = max;
        long mid;
        while (low <= high) {
            mid = (low + high) / 2;
            System.out.println("LOW: " + low + " " + "HIGH: " + high + " MID: " + mid);

            int count = 0;
            for (int i = 0; i < k; i++) {
                count += line[i] / mid;
            }
            if (count >= n) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("[RESULT]\nLOW: " + low + " HIGH: " + high);

        bw.write(low - 1 + "\n");
        bw.flush();
        bw.close();

    }
}