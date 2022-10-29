package boj;

/*
    곱셈의 분배법칙을 생각해보면 2 * (2 + 3 * 3) -> 2 * 2 + 2 * 3 * 3이 되는데 이를 적용해보자

    1. (, [ 괄호를 만나게 되면 해당 수들을 기존의 수에다가 게속 곱해간다. current *= 2 or current *= 3

    2. ), ]를 만나게 되면 직전에 방문한값에 따라 동작이 나뉜다.
        2-1. 직전의 값이 (, [ 라면 현재 수를 전체 합에 더해주고, 현재 완료된 괄호의 값을 제거해야 하므로
             현재 괄호에 맞는 값을 current에서 나눠준다.
             sum += current, current /= 3 or current /= 2
        2-2. 직전의 값이 ), ]라면 현재 괄호에 맞는 값을 current에서 나눠주기만 한다. current /= 3 or current /= 2

    3. 2의 과정중에서 스택이 비어있거나
       초기에 stack.peek()을 했을때 현재 괄호의 쌍이 맞지 않는 경우가 발생하면
           3-1. 여는 괄호 혹은 닫는 괄호의 숫자가 맞지 않거나
           3-2. 잘못된 괄호 배치 `(()[)]`와 같은 경우이므로 즉시 종료하고 0을 출력한다.
*/
import java.util.*;
import java.io.*;

class Boj2504 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        int current = 1;
        int sum = 0;
        boolean isWrong = false;
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '(') {
                current *= 2;
                st.push('(');
            } else if (c == '[') {
                current *= 3;
                st.push('[');
            } else if (c == ')') {
                if (st.isEmpty() || st.peek() == '[') {
                    isWrong = true;
                    break;
                }
                if (line.charAt(i - 1) == '(') {
                    sum += current;
                }
                current /= 2;
                st.pop();
            } else {
                if (st.isEmpty() || st.peek() == '(') {
                    isWrong = true;
                    break;
                }
                if (line.charAt(i - 1) == '[') {
                    sum += current;
                }
                current /= 3;
                st.pop();
            }
        }

        if (isWrong || !st.isEmpty()) {
            bw.write("0");
        } else {
            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
    }
}