package boj;

/*
    괄호도 하나의 연산자로 취급한다. 따라서 연산자 우선순위는 다음과 같다.
    ( * , / ) > ( +, - ) > ( ( )

    1. 피연산자들은 그대로 result에 붙여준다.
    2. (를 만나면 push
    3. )를 만나면 (를 만날때가지 pop을해 result에 붙여줌, 이후 pop을해 ( 버림
    4. 연산자를 만나면 검사를 한다.
        4-1. 스택이 비어있지 않고 스택 peek 연산자 우선순위 >= 현재 연산자 우선순위라면
           - 위 조건을 만족하지 않을때까지 stack을 pop해 result에 붙여준다.
           - 이후 현재 연산자를 push

    위의 과정을 통해 식을 전부 검사했음에도 스택이 비어있지 않다면 스택이 빌때까지 pop을 하여 result에 붙여준다.
*/

import java.io.*;
import java.util.*;

class Boj1918 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String formulas = br.readLine();

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char symbol : formulas.toCharArray()) {
            if (symbol >= 'A' && symbol <= 'Z') {
                result.append(symbol);
            } else if (symbol == '(') {
                stack.push(symbol);
            } else if (symbol == ')') {
                while (stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(symbol)) {
                    result.append(stack.pop());
                }
                stack.push(symbol);
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static int getPriority(char operator) {
        if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '(') {
            return 0;
        }
        return 1;
    }
}