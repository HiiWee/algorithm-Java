package boj;

import java.io.*;
import java.util.*;

class Boj19539 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int sum = 0;
        int twoCount = 0;
        int oneCount = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (n-- > 0) {
            int number = Integer.parseInt(st.nextToken());
            sum += number;
            twoCount += number / 2;
            oneCount += number % 2;
        }

        if (sum % 3 == 0 && twoCount >= oneCount) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();
    }
}
