package boj;

import java.io.*;
import java.util.StringTokenizer;

// 일반적인 팩토리얼과 소인수분해시 2의 개수가 5보다 적은 경우가 존재한다 ,125 Combination 1과 같은 경우
// 따라서 2와 5의 개수를 모두 카운트하고 더 작은 결과값을 반환한다.
class Boj2004 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int min = Math.min(getCount(n, m, 2), getCount(n, m, 5));
        bw.write(min + "\n");
        bw.flush();
        bw.close();

    }

    public static int getCount(int n, int m, int setNum) {
        int nCnt = 0;
        int mCnt = 0;
        for (long i = setNum; i <= 2000000000; i *= setNum) {
            nCnt += n / i;
            mCnt += m / i;
            mCnt += (n - m) / i;
        }
        return nCnt - mCnt;
    }

}