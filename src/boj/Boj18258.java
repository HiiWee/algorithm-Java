package boj;

import java.io.*;
import java.util.*;

class Boj18258 {
    static Deque<Integer> deque = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                push(Integer.parseInt(st.nextToken()));
            } else if (command.equals("pop")) {
                pop();
            } else if (command.equals("size")) {
                size();
            } else if (command.equals("empty")) {
                empty();
            } else if (command.equals("front")) {
                front();
            } else if (command.equals("back")) {
                back();
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void push(int num) {
        deque.offer(num);
    }

    public static void pop() {
        if (deque.isEmpty()) {
            sb.append(-1);
        } else {
            sb.append(deque.poll());
        }
        sb.append("\n");
    }

    public static void size() {
        sb.append(deque.size()).append("\n");
    }

    public static void empty() {
        if (deque.isEmpty()) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("\n");
    }

    public static void front() {
        if (deque.isEmpty()) {
            sb.append("-1");
        } else {
            sb.append(deque.peekFirst());
        }
        sb.append("\n");
    }

    public static void back() {
        if (deque.isEmpty()) {
            sb.append("-1");
        } else {
            sb.append(deque.peekLast());
        }
        sb.append("\n");
    }
}