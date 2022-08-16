package boj;

/*
    단순하게 3중 for문으로 풀 수 있을듯?
*/

import java.util.*;
import java.io.*;

class Boj18312 {
    static int n;
    static int k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int hour = 0; hour <= n; hour++) {
            if (isHavingNum(hour)) {
                sum += 60 * 60;
                continue;
            }
            for (int min = 0; min <= 59; min++) {
                if (isHavingNum(min)) {
                    sum += 60;
                    continue;
                }
                for (int sec = 0; sec <= 59; sec++) {
                    if (isHavingNum(sec)) {
                        sum++;
                    }
                }
            }
        }
        bw.write(sum + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean isHavingNum(int num) {
        int mod = num;
        int val;
        for (int i = 0; i < 2; i++) {
            val = mod % 10;
            mod /= 10;
            if (val == k) {
                return true;
            }
        }
        return false;
    }
}