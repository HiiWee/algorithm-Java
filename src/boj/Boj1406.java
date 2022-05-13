package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

// 복잡도 및 시간을 줄일 수 있는 방법 숙지

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] arr = br.readLine().toCharArray();
        int m = Integer.parseInt(br.readLine());

        List<Character> list = new LinkedList<>();
        ListIterator<Character> cursor = list.listIterator();
        for (char c : arr) {
            cursor.add(c);
        }

        for (int i = 0; i < m; i++) {
            char[] commands = br.readLine().toCharArray();

            if (commands[0] == 'L') {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                }
            } else if (commands[0] == 'D') {
                if (cursor.hasNext()) {
                    cursor.next();
                }
            } else if (commands[0] == 'B') {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                    cursor.remove();
                }
            } else if (commands[0] == 'P') {
                cursor.add(commands[2]);
            }
        }

        for (char c : list) {
            bw.write(c);
        }
        bw.flush();
        bw.close();
    }
}