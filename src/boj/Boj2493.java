package boj;

/*
    단순 반복문을 이용하면 시간초과 -> nlogn이 아니라, 최악의 경우 n^2 ㅎㅎ

    각 타워는 높이와 순번을 저장하고 있어야한다.

    타워를 스택에 넣기전 요소를 n 스택에 top에 존재하는 타워가 m일떄
    - n의 높이 > m의 높이: m의 높이 >= n의 높이가 될때까지 pop
      - 만약 스택이 빈다면 0 입력후, n을 스택에 push
    - n의 높이 <= m의 높이: m의 순번 입력후, n을 스택에 push
*/

import java.io.*;
import java.util.*;

class Tower {
    int value, sequence;
    Tower(int value, int sequence) {
        this.value = value;
        this.sequence = sequence;
    }
}

class Boj2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Tower> stack = new Stack<>();

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int value = Integer.parseInt(st.nextToken());
            Tower currentTower = new Tower(value, i);

            while (true) {
                if (stack.isEmpty()) {
                    result.append("0 ");
                    stack.push(currentTower);
                    break;
                }
                if (currentTower.value > stack.peek().value) {
                    stack.pop();
                } else {
                    result.append(stack.peek().sequence).append(" ");
                    stack.push(currentTower);
                    break;
                }
            }
        }

        bw.write(result.toString().trim());
        bw.flush();
        bw.close();
    }
}