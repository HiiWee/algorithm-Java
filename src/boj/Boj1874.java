package boj;

import java.util.*;
import java.io.*;

class Boj1874 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int preNum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > preNum) {
                for (int j = preNum + 1; j <= num; j++) {
                    sb.append("+\n");
                    stack.push(j);
                }
                preNum = num;
            } else if (stack.peek() != num) {
                bw.write("NO");
                bw.flush();
                bw.close();
                return;
            }
            stack.pop();
            sb.append("-\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();


    }
}
