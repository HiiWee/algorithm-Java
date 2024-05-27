package boj;

import java.io.*;
import java.util.*;

class Boj12761 {

    static int a, b, n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] amount = {1, -1, a, -a, b, -b, a, b};
        int[] visited = new int[100001];

        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{n, 0});

        int answer = 0;
        while (!que.isEmpty()) {
            int[] current = que.poll();

            if (current[0] == m) {
                answer = current[1];
                break;
            }
            for (int i = 0; i < 8; i++) {
                int next = current[0] + amount[i];

                if (i >= 6) {
                    next = current[0] * amount[i];
                }
                if (next < 0 || next > 100000 || visited[next] == 1) {
                    continue;
                }

                que.offer(new int[]{next, current[1] + 1});
                visited[next] = 1;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }
}
