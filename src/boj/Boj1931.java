package boj;

import java.util.*;
import java.io.*;

class Boj1931 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        /**
         * 회의실은 끝나는 시간 기준으로 정렬하되
         * 만약 끝나는 시간이 동일하다면, 시작 시간을 기준으로 오름차순 정렬을 해준다.
         * 반례
         * 1 3
         * 8 8
         * 4 8
         * 만약 시작 시간 기준으로 정렬하지 않는다면 1 3 -> 8 8로 회의실을 두 번 밖에 사용하지 못함
         */
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        // 이전 회의의 끝나는 시간
        int prevTime = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            // 만약 이전회의의 끝나는 시간과 현재의 시작시간이 겹치지 않는다면
            if (prevTime <= arr[i][0]) {
                count++;
                prevTime = arr[i][1];
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }
}