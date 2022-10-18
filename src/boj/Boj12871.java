package boj;

/*
    두 무한 문자열이 같은지를 판별하기 위해서는 동일 길이일 경우에 비교해야 한다.
    따라서 두 문자열 길이의 최소공배수를 구하고 최소공배수와 두 문자열의 길이가 동일해질때까지 문자열을 늘려주고
    두 문자열을 비교한다.
*/
import java.io.*;

class Boj12871 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        String t = br.readLine();

        int sLen = s.length();
        int tLen = t.length();
        int gcd;
        if (sLen > tLen) {
            gcd = getGCD(sLen, tLen);
        } else {
            gcd = getGCD(tLen, sLen);
        }

        int lcm = sLen * tLen / gcd;

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        while (sb1.length() < lcm) {
            sb1.append(s);
        }
        while (sb2.length() < lcm) {
            sb2.append(t);
        }

        if (sb1.toString().equals(sb2.toString())) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        bw.flush();
        bw.close();
    }

    public static int getGCD(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return getGCD(b, a % b);
    }
}