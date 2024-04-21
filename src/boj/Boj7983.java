package boj;

import java.io.*;
import java.util.*;

class Boj7983 {

    static class Job implements Comparable<Job> {
        int cost, limit;

        public Job(int cost, int limit) {
            this.cost = cost;
            this.limit = limit;
        }

        @Override
        public int compareTo(Job job) {
            return job.limit - limit;
        }
    }

    static PriorityQueue<Job> que = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            que.offer(new Job(cost, limit));
        }

        Job job = que.poll();
        int atLeastTime = job.limit - job.cost;
        while (!que.isEmpty()) {
            job = que.poll();

            if (atLeastTime > job.limit) {
                atLeastTime = job.limit - job.cost;
            } else {
                atLeastTime -= job.cost;
            }
        }

        bw.write(Integer.toString(Math.max(atLeastTime, -1)));
        bw.flush();
        bw.close();
    }

}

