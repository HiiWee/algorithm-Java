package boj;

import java.io.*;
import java.util.*;

class Boj14248 {

    static int n;
    static int[] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        int start = Integer.parseInt(br.readLine()) - 1;

        int count = countAllStone(start);

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    private static int countAllStone(final int start) {
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        que.offer(start);

        visited[start] = true;
        int count = 1;

        while (!que.isEmpty()) {
            int current = que.poll();

            int next = current + map[current];
            int prev = current - map[current];

            if (next < n && !visited[next]) {
                count++;
                visited[next] = true;
                que.offer(next);
            }
            if (prev >= 0 && !visited[prev]) {
                count++;
                visited[prev] = true;
                que.offer(prev);
            }
        }
        return count;
    }
}
