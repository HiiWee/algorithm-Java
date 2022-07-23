package boj;

/*
    우선순위 큐를 이용하자!!
    일단 중요한것은 가장 먼저 시작하는 회의가 우선순위를 가진다.
    따라서 시작시간을 기준으로 오름차순 정렬한 후 우선순위큐에 하나씩 담는다(낮은 정수가 우선순위 가짐)
    i 회의 끝나는 시간과 j 회의의 시작하는 시간을 비교했을때 val[i] <= val[j]라면 회의실을 하나만 사용할 수 있다.

    연속적으로 사용할 수 있다면 해당 회의는 poll()하고 현재 회의의 end값을 que에 push
    연속적으로 사용할 수 없다면 que에 현재 회의의 end 값을 push 한다
*/

import java.util.*;
import java.io.*;

class Boj19598 {
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
        Arrays.sort(time, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> que = new PriorityQueue<>();
        que.add(time[0][1]);
        for (int i = 1; i < n; i++) {
            int endTime = que.peek();

            if (endTime <= time[i][0]) {
                que.poll();
            }
            que.add(time[i][1]);
        }

        bw.write(que.size() + "\n");
        bw.flush();
        bw.close();
    }
}