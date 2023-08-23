package boj;

import java.io.*;
import java.util.*;

class Boj22945 {
    private static int[] power;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        power = new int[n];
        for (int i = 0; i < n; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        int maxPower = 0;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            maxPower = Math.max(calcPower(l, r), maxPower);
            if (power[l] < power[r]) {
                l++;
            } else {
                r--;
            }
        }

        bw.write(maxPower + "");
        bw.flush();
        bw.close();
    }

    public static int calcPower(int l, int r) {
        int countOfPerson = r - l - 1;
        return countOfPerson * Math.min(power[l], power[r]);
    }
}