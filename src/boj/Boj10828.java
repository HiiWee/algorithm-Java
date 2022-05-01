package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

class Boj10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            String[] temp = bf.readLine().split(" ");
            String op = temp[0];
            switch (op) {
                case "push":
                    stack.push(Integer.parseInt(temp[1]));
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    } catch (EmptyStackException e) {
                        System.out.println(-1);
                    }
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    if (stack.empty()) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }
                    break;
                case "top":
                    try {
                        System.out.println(stack.peek());
                    } catch (EmptyStackException e) {
                        System.out.println(-1);
                    }
                    break;
            }
        }
    }
}