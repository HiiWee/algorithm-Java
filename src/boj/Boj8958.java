package boj;

import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while(t-- > 0) {
            char[] results = br.readLine().toCharArray();
            int sum = 0;
            int count = 0;
            for (char result : results) {
                if (result == 'O') {
                    count++;
                } else {
                    count = 0;
                }
                sum += count;
            }
            answer.append(sum).append("\n");
        }
        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}