package boj;

import java.util.*;
import java.io.*;

class Boj20444 {
    static long n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

        // 세로, 가로로 자르는 수가 동일할떄 최대의 종이의 개수를 얻으므로
        // 한쪽 방향 기준 0 ~ n / 2까지가 최소 ~ 최대의 개수를 얻을 수 있는 범위가 된다.
        long low = 0;
        long high = n / 2;
        long mid;
        while (low < high) {
            mid = (low + high) / 2;

            long total = 0;
            total += mid + 1;
            total = total * (n - mid + 1);

            if (total >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        long count = 0;
        count += low + 1;
        count = count * (n - low + 1);

        if (count == k) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }

        bw.flush();
        bw.close();
    }
}
