package boj;

import java.io.*;
import java.util.*;

class Boj15566 {

    static class Node {
        int number, subject;

        public Node(int number, int subject) {
            this.number = number;
            this.subject = subject;
        }
    }

    static class Frog {
        private final int number;
        private final int[] interests = new int[4];
        private final int[] favorite = new int[2];

        public Frog(int number) {
            this.number = number;
        }
    }

    static int n, m;
    static Frog[] frogs;
    static Frog[] flowerVisited;
    static List<Node>[] flowers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        frogs = new Frog[n];
        flowerVisited = new Frog[n];
        flowers = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            frogs[i] = new Frog(i + 1);
            frogs[i].interests[0] = Integer.parseInt(st.nextToken());
            frogs[i].interests[1] = Integer.parseInt(st.nextToken());
            frogs[i].interests[2] = Integer.parseInt(st.nextToken());
            frogs[i].interests[3] = Integer.parseInt(st.nextToken());
            flowers[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            frogs[i].favorite[0] = a;
            frogs[i].favorite[1] = b;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;

            flowers[a].add(new Node(b, t));
            flowers[b].add(new Node(a, t));
        }

        search(0);

        bw.write("NO");
        bw.flush();
        bw.close();
    }

    public static void search(int frogIdx) {
        if (frogIdx == n) {
            StringBuilder answer = new StringBuilder();
            answer.append("YES\n");
            for (Frog frog : flowerVisited) {
                answer.append(frog.number).append(" ");
            }
            System.out.println(answer);
            System.exit(0);
            return;
        }

        for (int prefer : frogs[frogIdx].favorite) {
            if (flowerVisited[prefer] != null) {
                continue;
            }
            if (isPossible(prefer, frogIdx)) {
                flowerVisited[prefer] = frogs[frogIdx];
                search(frogIdx + 1);
                flowerVisited[prefer] = null;
            }
        }
    }

    public static boolean isPossible(int flowerIdx, int frogIdx) {
        for (Node node : flowers[flowerIdx]) {
            if (flowerVisited[node.number] != null
                    && frogs[frogIdx].interests[node.subject] != flowerVisited[node.number].interests[node.subject]) {
                return false;
            }
        }
        return true;
    }
}
