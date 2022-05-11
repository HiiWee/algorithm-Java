package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Boj10866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> deque = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            String command = line[0];

            if (command.equals("push_front")) {
                deque.add(0, Integer.parseInt(line[1]));
            } else if (command.equals("push_back")) {
                deque.add(deque.size(), Integer.parseInt(line[1]));
            } else if (command.equals("pop_front")) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.get(0));
                    deque.remove(0);
                }
            } else if (command.equals("pop_back")) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.get(deque.size() - 1));
                    deque.remove(deque.size() - 1);
                }
            } else if (command.equals("size")) {
                System.out.println(deque.size());
            } else if (command.equals("empty")) {
                if (deque.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (command.equals("front")) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.get(0));
                }
            } else if (command.equals("back")) {
                if (deque.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(deque.get(deque.size() - 1));
                }
            }
        }
    }
}
