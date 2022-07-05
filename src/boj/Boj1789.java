package boj;

import java.io.*;

// 이진탐색 풀이방법
class Boj1789_1 {
    static long N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Long.parseLong(br.readLine());

        long low = 1;
        long high = N + 1;
        long mid;
        while (low < high) {
            mid = (low + high) / 2;

            long total = 0;
            for (long i = 1; i <= mid; i++) {
                total += i;
            }

            if (total > N) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.write((low - 1) + "\n");
        bw.flush();
        bw.close();
    }

}

// 그리디 풀이
class Boj1789 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Long.parseLong(br.readLine());
        if (N == 1) {
            bw.write("1");
            bw.flush();
            bw.close();
            return;
        }
        long total = 0;
        int count = 0;
        for (long i = 1; i <= N; i++) {
            total += i;
            count++;
            if (total > N) {
                break;
            }
        }

        bw.write(--count + "\n");
        bw.flush();
        bw.close();
    }
}
