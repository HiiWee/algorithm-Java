package boj;

/*
    DFS를 통해 모든 경우를 탐색할 수 있다.

*/

import java.io.*;

class Boj16637 {
    static int n;
    static String line;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        line = br.readLine();

        DFS(0, 0);

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void DFS(int index, int value) {
        if (index >= n) {
            max = Math.max(max, value);
            return;
        }

        char op;
        if (index == 0) {
            op = '+';
        } else {
            op = line.charAt(index - 1);
        }

        if (index + 2 < n) {
            int bracket = calculator(line.charAt(index) - '0', line.charAt(index + 2) - '0', line.charAt(index + 1));
            DFS(index + 4, calculator(value, bracket, op));
        }
        DFS(index + 2, calculator(value, line.charAt(index) - '0', op));
    }

    public static int calculator(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result =  num1 + num2;
                break;
            case '-':
                result =  num1 - num2;
                break;
            case '*':
                result =  num1 * num2;
                break;
        }
        return result;
    }
}