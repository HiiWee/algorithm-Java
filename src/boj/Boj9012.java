package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// 내 풀이
class Boj9012_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        int n = Integer.parseInt(bf.readLine());
        String ps;
        for (int i = 0; i < n; i++) {
            ps = bf.readLine();
            for (int j = 0; j < ps.length(); j++) {
                char brace = ps.charAt(j);
                if (brace == '(') {
                    stack.push(brace);
                } else {
                    try {
                        stack.pop();
                    } catch (EmptyStackException e) {
                        System.out.println("NO");
                        break;
                    }
                }
                if (j == ps.length() - 1) {
                    if (stack.empty()) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                        stack.clear();
                    }
                }
            }

        }

    }
}
// 개인적으로 깔끔하다고 생각하는 풀이
class Boj9012_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        int n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            System.out.println(solve(bf.readLine()));
        }
    }

    public static String solve(String ps) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < ps.length(); i++) {
            char brace = ps.charAt(i);
            if (brace == '(') {
                stack.push(brace);
            } else if (stack.empty()) {
                return "NO";
            } else {
                stack.pop();
            }
        }

        if (stack.empty()) {
            return "YES";
        } else {
            return "NO";
        }
    }
}