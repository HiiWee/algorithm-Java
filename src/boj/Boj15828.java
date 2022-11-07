package boj;

import java.io.*;
import java.util.*;

class Boj15828 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int bufferSize = Integer.parseInt(br.readLine());

        Queue<Integer> que = new LinkedList<>();

        int currentSize = 0;
        int input;
        while ((input = Integer.parseInt(br.readLine())) != -1) {
            if (input == 0) {
                currentSize--;
                que.poll();
                continue;
            }
            if (input > 0 && currentSize < bufferSize) {
                currentSize++;
                que.offer(input);
            }
        }
        if (que.isEmpty()) {
            bw.write("empty");
        } else {
            while (!que.isEmpty()) {
                bw.write(que.poll() + " ");
            }
        }
        bw.flush();
        bw.close();
    }
}