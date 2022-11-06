package boj;
/*
    문자열이 균형잡혀 있기 위해서는 좌, 우 괄호의 수가 동일해야 한다.
    균형잡혀있지 않은 문자열은 다음과 같이 두 가지 경우가 존재한다.
    1. 왼쪽 괄호가 더 많은 경우
        - 모든 짝을 맞추고 나서 남는 괄호가 있으면 안된다.
    2. 우측 괄호가 더 많은 경우
        - 우측괄호가 나타나 스택을 확인했는데 스택이 비어있으면 안된다.
    3. 괄호의 짝이 맞지 않는 경우
        - 짝을 맞추려고 스택을 확인했는데 현재 우측괄호와 다른 괄호가 들어있으면 안된다.

*/

import java.io.*;
import java.util.*;

class Boj4949 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        String input;
        while (!(input = br.readLine()).equals(".")) {
            Stack<Character> stack = new Stack<>();
            boolean flag = false;
            for (char c : input.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty()) {
                        sb.append("no\n");
                        flag = true;
                        break;
                    }
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        sb.append("no\n");
                        flag = true;
                        break;
                    }
                } else if (c == ']') {
                    if (stack.isEmpty()) {
                        sb.append("no\n");
                        flag = true;
                        break;
                    }
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        sb.append("no\n");
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag && stack.isEmpty()) {
                sb.append("yes\n");
            } else if (!flag && !stack.isEmpty()) {
                sb.append("no\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}