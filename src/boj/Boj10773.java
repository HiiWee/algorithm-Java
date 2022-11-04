package boj;

/*
    스택을 이용해서 0을 만나면 pop을 하고
    다른숫자를 만나면 push를 한다.
    이후 최종적으로 stack에 남아있는 값을 다 더해서 출력한다.

    기능목록
        1. 0을 만나면 pop 한다.
        2. 0이 아닌 숫자를 만나면 push 한다.
        3. 모든 입력이 종료되고 스택에있는 값을 더한다.
        4. 정수가 "0"일 경우에 지울 수 있는 수가 있음을 보장할 수 있다.

*/

import java.io.*;
import java.util.*;

class Boj10773 {
    static Stack<Integer> integerStack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int number = Integer.parseInt(br.readLine());
            if (number == 0) {
                integerStack.pop();
                continue;
            }
            integerStack.push(number);
        }
        int result = getSumOfStack();

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static int getSumOfStack() {
        int sum = 0;
        while (!integerStack.isEmpty()) {
            sum += integerStack.pop();
        }
        return sum;
    }
}