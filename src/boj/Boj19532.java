package boj;

/*
    단순하게 연립방정식의 해를 구하여 그대로 대입하면 된다.
    x = (e*c - b*f) / (a*e - b*d)
    y = (d*c - a*f) / (b*d - a*e)
*/

import java.io.*;
import java.util.*;

// 연립방정식 풀이
class Boj19532_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a, b, c, d, e, f;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        int x = (e * c - b * f) / (a * e - b * d);
        int y = (d * c - a * f) / (b * d - a * e);

        bw.write(x + " " + y + "\n");
        bw.flush();
        bw.close();
    }
}

/*
    혹은 브루트 포스한 방법은 O(n^2)으로 전부 대입한다.
*/

// 브루트 포스 풀이
class Boj19532_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a, b, c, d, e, f;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        for (int x = -999; x <= 999; x++) {
            for (int y = -999; y <= 999; y++) {
                if (a * x + b * y == c && d * x + e * y == f) {
                    bw.write(x + " " + y + "\n");
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
    }
}
