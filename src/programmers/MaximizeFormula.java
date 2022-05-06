package programmers;

import java.util.Stack;

class Solution {
    // 연산자
    static Stack<Character> opStack;
    // 피연산자
    static Stack<Long> numStack;
    public static long solution(String expression) {
        String[] priority = {"*+-", "*-+", "+*-", "+-*", "-*+", "-+*"};
        long[] result = new long[priority.length];
        // 연산자
        opStack = new Stack<>();
        // 피연산자
        numStack = new Stack<>();

        for (int i = 0; i < priority.length; i++) {
            long num = 0L;
            for (int j = 0; j < expression.length(); j++) {
                char c = expression.charAt(j);
                switch (c) {
                    case '*':
                        numStack.push(num);
                        num = 0;
                        calc(priority[i], '*');
                        break;
                    case '+':
                        numStack.push(num);
                        num = 0;
                        calc(priority[i], '+');
                        break;
                    case '-':
                        numStack.push(num);
                        num = 0;
                        calc(priority[i], '-');
                        break;
                    // 숫자
                    default:
                        num = num * 10 + c - '0';
                        break;
                }
            }
            numStack.push(num);
            while (!opStack.empty()) {
                long secondNum = numStack.pop();
                long firstNum = numStack.pop();
                char operand = opStack.pop();

                if (operand == '*') {
                    numStack.push(secondNum * firstNum);
                } else if (operand == '+') {
                    numStack.push(firstNum + secondNum);
                } else if (operand == '-') {
                    numStack.push(firstNum - secondNum);
                }
            }
            result[i] = numStack.pop();
        }
        long max = Math.abs(result[0]);
        for (int i = 1; i < result.length; i++) {
            max = Math.max(max, Math.abs(result[i]));
        }
            return max;
    }

    public static void calc(String priority, char op) {
        if (opStack.isEmpty()) {
            opStack.push(op);
            return;
        }
        // 스택에 든것이 더 큰 우선순위라면 연산
        if (priority.indexOf(opStack.peek()) <= priority.indexOf(op)) {
            long secondNum = numStack.pop();
            long firstNum = numStack.pop();
            char operand = opStack.pop();

            if (operand == '*') {
                numStack.push(secondNum * firstNum);
            } else if (operand == '+') {
                numStack.push(firstNum + secondNum);
            } else if (operand == '-') {
                numStack.push(firstNum - secondNum);
            }
            // 스택의 top은 다른 연산자로 변경되었으므로 우선순위 재판단을 위한 재귀호출
            calc(priority, op);
        } else {
            opStack.push(op);
        }
    }

    public static void main(String[] args) {
        String[] expressions = {"100-200*300-500+20", "50*6-3*2"};
        long[] results = {60420L, 300L};

        for (int i = 0; i < expressions.length; i++) {
            long answer = solution(expressions[i]);
            if (answer == results[i]) {
                System.out.println("Test " + (i + 1) + " passed");
                System.out.println("answer:" + answer);
                System.out.println("results:" + results[i]);
                System.out.println();
            }
        }
    }
}
