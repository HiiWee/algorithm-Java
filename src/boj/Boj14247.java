package boj;

import java.io.*;
import java.util.*;

class Boj14247 {

    static class Node implements Comparable<Node> {
        int h, a;

        public Node(int h, int a) {
            this.h = h;
            this.a = a;
        }

        @Override
        public int compareTo(Node node) {
            if (node.a == a) {
                return h - node.h;
            }
            return a - node.a;
        }
    }

    static int n;
    static PriorityQueue<Node> que = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            que.offer(new Node(
                    Integer.parseInt(st1.nextToken()),
                    Integer.parseInt(st2.nextToken())
            ));
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            Node node = que.poll();
            answer += node.h + i * node.a;
        }
        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
    }
}
