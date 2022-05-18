package boj;

import java.io.*;
import java.util.StringTokenizer;

class Boj11576 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int newMethod = Integer.parseInt(st.nextToken());
        int oldMethod = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int decimal = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            decimal += Math.pow(newMethod, n - 1 - i) * Integer.parseInt(st.nextToken());
        }
        while (true) {
            int other = decimal % oldMethod;
            int result = decimal / oldMethod;
            sb.append(other).append(" ");
            if (result < oldMethod) {
                sb.append(result);
                break;
            }
            decimal = result;
        }
        String[] answer = sb.toString().split(" ");
        for (int i = 0; i < answer.length; i++) {
            bw.write(answer[answer.length - 1 - i] + " ");
        }
        bw.flush();
        bw.close();
    }
}