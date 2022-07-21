package boj;

import java.util.*;
import java.io.*;

class Boj11000 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] time = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.parseInt(st.nextToken());
            time[i][1] = Integer.parseInt(st.nextToken());
        }

        // 먼저 시작하는 강의가 강의실을 선점해야 하기 때문에
        // 강의가 시작하는 시간이 빠른순으로 오름차순 정렬한다.
        Arrays.sort(time, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 정수 작은 순서부터 우선순위
        // 가장 먼저 시작하는 수업 미리 넣기
        pq.add(time[0][1]);
        for (int i = 1; i < n; i++) {
            int endTime = pq.peek();

            // 만약 그 다음에 시작하는 강의의 시작 시간이, 직전에 시작한 수업의 끝나는 시간보다 크다면,
            // 하나의 강의실을 두 수업 모두 사용 가능
            if (endTime <= time[i][0]) {
                pq.poll();
                pq.add(time[i][1]);
            } else {
                pq.add(time[i][1]);
            }
        }
        int count = pq.size();

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}