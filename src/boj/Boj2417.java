package boj;

import java.io.*;
import java.math.BigInteger;

// BigInteger 이용, Lower Bound
class Boj2417_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        long low = 0;
        long high = n;
        long mid;
        while (low < high) {
            mid = (low + high) / 2;
            BigInteger bigMid = BigInteger.valueOf(mid * mid);

            if (bigMid.compareTo(BigInteger.valueOf(n)) >= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.write(low + "\n");
        bw.flush();
        bw.close();
    }
}

// long 타입 풀이 Lower Bound
class Boj2417_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        long low = 0;
        long high = n;
        long mid;
        while (low < high) {
            mid = (low + high) / 2;

            long value = (long) Math.pow(mid, 2);

            if (value >= n) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.write(low + "\n");
        bw.flush();
        bw.close();
    }
}

