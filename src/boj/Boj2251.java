package boj;

import java.util.*;
import java.io.*;

// DFS 풀이
class Boj2251_1 {
    static int[] limit;
    static boolean[][] capacity;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        limit = new int[3];
        // a, b용량만 결정하면 c는 신경쓰지 않아도 됨
        capacity = new boolean[201][201];
        set = new TreeSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }
        DFS(0, 0, limit[2]);

        for (int num : set) {
            bw.write(num + " ");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int a, int b, int c) {
        if (capacity[a][b]) {
            return;
        }
        if (a == 0) {
            set.add(c);
        }
        capacity[a][b] = true;

        // a -> b
        if (a + b > limit[1]) {
            DFS(a + b - limit[1], limit[1], c);
        } else {
            DFS(0, a + b, c);
        }
        // a -> c
        if (a + c > limit[2]) {
            DFS(a + c - limit[2], b, limit[2]);
        } else {
            DFS(0, b, a + c);
        }
        // b -> c
        if (b + c > limit[2]) {
            DFS(a, b + c - limit[2], limit[2]);
        } else {
            DFS(a, 0, b + c);
        }
        // b -> a
        if (b + a > limit[0]) {
            DFS(limit[0], b + a - limit[0], c);
        } else {
            DFS(b + a, 0, c);
        }
        // c -> a
        if (c + a > limit[0]) {
            DFS(limit[0], b, c + a - limit[0]);
        } else {
            DFS(c + a, b, 0);
        }
        // c -> b
        if (c + b > limit[1]) {
            DFS(a, limit[1], c + b - limit[1]);
        } else {
            DFS(a, c + b, 0);
        }

    }
}

// BFS 풀이
class Boj2251_2 {
    static int[] limit;
    static boolean[][] capacity;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        limit = new int[3];
        // a, b용량만 결정하면 c는 신경쓰지 않아도 됨
        capacity = new boolean[201][201];
        set = new TreeSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }
        BFS(0, 0, limit[2]);

        for (int num : set) {
            bw.write(num + " ");
        }
        bw.flush();
        bw.close();
    }

    public static void BFS(int a, int b, int c) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{a, b, c});

        while (!que.isEmpty()) {
            int[] bottle = que.poll();

            if (capacity[bottle[0]][bottle[1]]) {
                continue;
            }
            capacity[bottle[0]][bottle[1]] = true;

            if (bottle[0] == 0) {
                set.add(bottle[2]);
            }
            a = bottle[0];
            b = bottle[1];
            c = bottle[2];
            // a -> b
            if (a + b > limit[1]) {
                que.add(new int[]{a + b - limit[1], limit[1], c});
            } else {
                que.add(new int[]{0, a + b, c});
            }
            // c -> b
            if (c + b > limit[1]) {
                que.add(new int[]{a, limit[1], c + b - limit[1]});
            } else {
                que.add(new int[]{a, c + b, 0});
            }
            // a -> c
            if (a + c > limit[2]) {
                que.add(new int[]{a + c - limit[2], b, limit[2]});
            } else {
                que.add(new int[]{0, b, a + c});
            }
            // b -> c
            if (b + c > limit[2]) {
                que.add(new int[]{a, b + c - limit[2], limit[2]});
            } else {
                que.add(new int[]{a, 0, b + c});
            }
            // b -> a
            if (b + a > limit[0]) {
                que.add(new int[]{limit[0], b + a - limit[0], c});
            } else {
                que.add(new int[]{b + a, 0, c});
            }
            // c -> a
            if (c + a > limit[0]) {
                que.add(new int[]{limit[0], b, c + a - limit[0]});
            } else {
                que.add(new int[]{c + a, b, 0});
            }
        }
    }
}
