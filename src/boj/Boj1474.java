package boj;

import java.io.*;
import java.util.*;

class Boj1474 {

    static int n, m;
    static String[] words;
    static int[] underBar;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        words = new String[n];
        underBar = new int[n];

        int length = 0;
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            length += words[i].length();
        }
        int common = (m - length) / (n - 1);
        int left = (m - length) % (n - 1);

        StringBuilder commonUnderBar = new StringBuilder();
        while (common-- > 0) {
            commonUnderBar.append("_");
        }

        for (int i = 0; i < n - 1; i++) {
            char next = words[i + 1].charAt(0);
            if (left > 0 && next >= 'a' && next <= 'z') {
                words[i] = words[i] + "_";
                left--;
            }
            words[i] = words[i] + commonUnderBar;
        }
        if (left > 0) {
            for (int i = n - 2; i >= 0; i--) {
                char next = words[i + 1].charAt(0);

                if (next >= 'A' && next <= 'Z') {
                    words[i] = words[i] + "_";
                    left--;
                    if (left == 0) {
                        break;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer.append(words[i]);
        }
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
