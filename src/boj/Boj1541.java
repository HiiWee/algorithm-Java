package boj;

/*
    최소로 만들기 위해서는 먼저 마이너스 연산만 남기고 덧셈 연산은 우선적으로 시행하고, 마이너스만 남기고
    마지막에 순차적으로 마이너스 연산을 하면 완료됨
*/

import java.util.*;
import java.io.*;

// 연산자 스택과 피연산자 스택을 이용해 우선순위를 +로 두고 풀이 --> 다소 복잡
class Boj1541_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();

        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        int num = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '+') {
                // 만약 스택에 넣은 연산자가 +면 계산함
                if (operator.size() > 0 && operator.peek() == '+') {
                    operator.pop();
                    int num1 = operand.pop();
                    operand.push(num + num1);
                    operator.push(c);
                } else {
                    operand.push(num);
                    operator.push(c);
                }
                num = 0;
            } else if (c == '-') {
                if (operator.size() > 0 && operator.peek() == '+') {
                    operator.pop();
                    int num1 = operand.pop();
                    operand.push(num + num1);
                    operator.push(c);
                } else {
                    operator.push(c);
                    operand.push(num);
                }
                num = 0;
            } else {
                num = num * 10 + c - '0';
            }
            if (i == line.length() - 1) {
                if (operator.size() > 0 && operator.peek() == '+') {
                    operator.pop();
                    int num2 = operand.pop();
                    operand.push(num + num2);
                } else {
                    operand.push(num);
                }
            }
        }
        int result;
        if (operand.size() == 1) {
            result = operand.pop();
        } else {
            int[] arr = new int[operand.size()];
            int size = operand.size();
            for (int i = 0; i < size; i++) {
                arr[size - 1 - i] = operand.pop();
            }
            result = arr[0];
            for (int i = 1; i < size; i++) {
                result = result - arr[i];
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}

// -연산을 제외한 모든 +연산을 미리 계산하고 마지막에 계산된 수를 이용해 -연산을 순차적으로 계산하면 최소값 얻음
class Boj1541_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");

        int[] arr = new int[st.countTokens()];
        int size = st.countTokens();

        for (int i = 0; i < size; i++) {
            String line = st.nextToken();
            StringTokenizer st2 = new StringTokenizer(line, "+");
            int num = Integer.parseInt(st2.nextToken());
            int size2 = st2.countTokens();
            for (int j = 0; j < size2; j++) {
                num += Integer.parseInt(st2.nextToken());
            }
            arr[i] = num;
        }
        int result = arr[0];
        for (int i = 1; i < size; i++) {
            result -= arr[i];
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}