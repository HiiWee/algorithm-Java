package boj;

import java.io.*;
import java.util.*;

class Boj14400 {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] xPos;
    static int[] yPos;
    static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        xPos = new int[n];
        yPos = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.add(new Node(x, y));
            xPos[i] = x;
            yPos[i] = y;
        }

        Arrays.sort(xPos);
        Arrays.sort(yPos);
        int midX = xPos[(n - 1) / 2];
        int midY = yPos[(n - 1) / 2];

        long answer = 0;
        for (Node node : nodes) {
            answer += Math.abs(node.x - midX) + Math.abs(node.y - midY);
        }

        bw.write(Long.toString(answer));
        bw.flush();
        bw.close();
    }
}
