package boj;

import java.io.*;

class Boj13413 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String line1 = br.readLine();
            String line2 = br.readLine();

            int count1 = 0;
            int count2 = 0;
            for (int i = 0; i < n; i++) {
                if (line1.charAt(i) != line2.charAt(i)) {
                    if (line1.charAt(i) == 'B') {
                        count1++;
                    } else {
                        count2++;
                    }
                }
            }
            answer.append(Math.abs(count1 - count2) + Math.min(count1, count2)).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}

