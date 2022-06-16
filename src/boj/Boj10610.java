package boj;

import java.util.*;
import java.io.*;

class Boj10610 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String num = br.readLine();
        char[] arr = num.toCharArray();
        int total = 0;
        int zeroCount = 0;
        for (int i = 0; i < num.length(); i++) {
            total += arr[i] - '0';
            if (arr[i] == '0') {
                zeroCount++;
            }
        }

        if (total % 3 != 0 || zeroCount == 0) {
            bw.write(-1 + "\n");
            bw.flush();
            bw.close();
            return;
        }
        Arrays.sort(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            bw.write(arr[i] + "");
        }
        bw.flush();
        bw.close();
    }
}
