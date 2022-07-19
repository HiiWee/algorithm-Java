package boj;

/*
    최대가 되려면 MMMK 길이가 최대한 길어야 하고, 만약 M밖에 없다면 따로따로 계산
    최소가 되려면 M은 M끼리 K는 K끼리 뭉쳐야 최소가 됨
    3000개의 자리수 -> long타입도 불가 --> 문자열을 이용해야함
*/

import java.io.*;

class Boj21314 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();

        int size = line.length();
        StringBuilder minSb = new StringBuilder();
        StringBuilder maxSb = new StringBuilder();
        int mCnt = 0;
        int kCnt = 0;
        for (int i = 0; i < size; i++) {
            char c = line.charAt(i);

            if (c == 'M') {
                mCnt++;
            } else {
                if (mCnt > 0) {
                    maxSb.append(5);
                    minSb.append(1);
                    for (int j = 0; j < mCnt; j++) {
                        maxSb.append(0);
                        if (j > 0) {
                            minSb.append(0);
                        }
                    }
                    minSb.append(5);
                    mCnt = 0;
                } else {
                    maxSb.append(5);
                    minSb.append(5);
                }
            }
        }
        if (mCnt > 0) {
            minSb.append(1);
            for (int i = 0; i < mCnt; i++) {
                maxSb.append(1);
                if (i > 0) {
                    minSb.append(0);
                }
            }
        }
        maxSb.append("\n");
        minSb.append("\n");

        bw.write(maxSb.toString() + minSb.toString());
        bw.flush();
        bw.close();

    }
}