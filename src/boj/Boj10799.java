package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bar = br.readLine();
        Stack<Character> stack = new Stack<>();

        int count = 0;
        char preC = 'N';
        for (int i = 0; i < bar.length(); i++) {
            char c = bar.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                // 레이저로 커팅
                if (preC == '(') {
                    stack.pop();
                    count += stack.size();
                } else if (preC != 'N') {
                    stack.pop();
                    count++;
                }
            }

            preC = c;
        }
        System.out.println(count);
    }
}