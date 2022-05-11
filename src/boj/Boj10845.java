package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> que = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            String command = line[0];
            if (command.equals("push")) {
                que.add(Integer.parseInt(line[1]));
            } else if (command.equals("pop")) {
                if (que.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(que.get(0));
                    que.remove(0);
                }
            } else if (command.equals("size")) {
                System.out.println(que.size());
            } else if (command.equals("empty")) {
                if (que.isEmpty()) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (command.equals("front")) {
                if (que.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(que.get(0));
                }
            } else if (command.equals("back")) {
                if (que.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(que.get(que.size() - 1));
                }
            }
        }
    }
}
