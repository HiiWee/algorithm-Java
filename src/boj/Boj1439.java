package boj;

import java.io.*;

class Boj1439 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] lines = br.readLine().toCharArray();

        int oneCount = 0;
        int oneLength = 0;
        int zeroCount = 0;
        int zeroLength = 0;
        for (char c : lines) {
            if (c == '0') {
                if (oneLength > 0) {
                    oneCount++;
                    oneLength = 0;
                }
                zeroLength++;
            } else {
                if (zeroLength > 0) {
                    zeroCount++;
                    zeroLength = 0;
                }
                oneLength++;
            }
        }

        if (oneLength > 0) {
            oneCount++;
        }
        if (zeroLength > 0) {
            zeroCount++;
        }

        if (oneCount == 0 || zeroCount == 0) {
            bw.write("0");
        } else {
            bw.write(Integer.toString(Math.min(oneCount, zeroCount)));
        }
        bw.flush();
        bw.close();
    }
}