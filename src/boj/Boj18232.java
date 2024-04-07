package boj;

import java.io.*;
import java.util.*;

class Boj18232 {

    static class Node {
        int number, count;

        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    static int n, m;
    static int s, e;
    static List<Integer>[] teleport;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        teleport = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            teleport[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            teleport[x].add(y);
            teleport[y].add(x);
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(s, 0));
        visited[s] = true;
        int second = 0;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            if (curNode.number == e) {
                second = curNode.count;
                break;
            }

            for (int next : teleport[curNode.number]) {
                if (!visited[next]) {
                    que.offer(new Node(next, curNode.count + 1));
                    visited[next] = true;
                }
            }
            if (curNode.number - 1 > 0 && !visited[curNode.number - 1]) {
                que.offer(new Node(curNode.number - 1, curNode.count + 1));
                visited[curNode.number - 1] = true;
            }
            if (curNode.number + 1 <= n && !visited[curNode.number + 1]) {
                que.offer(new Node(curNode.number + 1, curNode.count + 1));
                visited[curNode.number + 1] = true;
            }
        }

        bw.write(Integer.toString(second));
        bw.flush();
        bw.close();
    }
}
