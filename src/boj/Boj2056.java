package boj;

// 위상 정렬 + DP 사용
import java.io.*;
import java.util.*;

class Boj2056_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[n + 1];
        int[] times = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int preNode = Integer.parseInt(st.nextToken());
                inDegree[i]++;
                graph[preNode].add(i);
            }
        }

        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            dp[i] = times[i];
            if (inDegree[i] == 0) {
                que.offer(i);
            }
        }

        while (!que.isEmpty()) {
            int curNode = que.poll();

            for (int nextNode : graph[curNode]) {
                inDegree[nextNode]--;
                dp[nextNode] = Math.max(dp[curNode] + times[nextNode], dp[nextNode]);
                if (inDegree[nextNode] == 0) {
                    que.offer(nextNode);
                }
            }
        }

        int maxTime = 0;
        for (int time : dp) {
            maxTime = Math.max(maxTime, time);

        }

        bw.write(maxTime + "");
        bw.flush();
        bw.close();
    }
}

// K작업의 선행작업은 반드시 K - 1번까지의 작업들로 구성되므로 굳이 위상정렬을 하지 않아도 됨
class Boj2056_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] times = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            times[i] = time;
            for (int j = 0; j < count; j++) {
                int preNode = Integer.parseInt(st.nextToken());
                times[i] = Math.max(times[i], times[preNode] + time);
            }
        }

        int maxTime = 0;
        for (int time : times) {
            maxTime = Math.max(maxTime, time);

        }

        bw.write(maxTime + "");
        bw.flush();
        bw.close();
    }
}