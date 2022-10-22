package boj;

import java.io.*;
import java.util.*;

class Boj1935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        double[] value = new double[n];

        for (int i = 0; i < n; i++) {
            value[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c >= 65 && c <= 90) {
                stack.push(value[c - 'A']);
            } else {
                stack.push(calculate(stack.pop(), stack.pop(), c));
            }
        }
        String result = String.format("%.2f", stack.pop());
        bw.write(result);
        bw.flush();
        bw.close();
    }

    public static double calculate(double val2, double val1, char op) {
        double result = 0;
        switch (op) {
            case '*':
                result = val1 * val2;
                break;
            case '/':
                result = val1 / val2;
                break;
            case '+':
                result = val1 + val2;
                break;
            case '-':
                result = val1 - val2;
                break;
        }
        return result;
    }
}