package boj;

import java.io.*;
import java.util.*;

class Boj12782 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String n = st.nextToken();
            String m = st.nextToken();

            int nOneCount = 0;
            int mOneCount = 0;
            for (int i = 0; i < n.length(); i++) {
                if (n.charAt(i) != m.charAt(i)) {
                    if (n.charAt(i) == '1') {
                        nOneCount++;
                    } else {
                        mOneCount++;
                    }
                }
            }
            int result = Math.abs(nOneCount - mOneCount) + Math.min(nOneCount, mOneCount);
            answer.append(result).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}