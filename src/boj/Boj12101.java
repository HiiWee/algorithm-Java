package boj;

import java.io.*;
import java.util.*;

class Boj12101 {

    static int n, k;
    static List<String> expressions = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        search(0, "");

        if (expressions.size() < k) {
            bw.write("-1");
            bw.flush();
            bw.close();
            return;
        }

        bw.write(expressions.get(k - 1));
        bw.flush();
        bw.close();
    }

    public static void search(int sum, String expression) {
        if (sum == n) {
            expressions.add(expression);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (sum + i <= n) {
                if (sum == 0) {
                    search(sum + i, String.valueOf(i));
                } else {
                    search(sum + i, expression + "+" + i);
                }
            }
        }
    }
}
