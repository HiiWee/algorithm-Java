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

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int count = 1;
        int endTime = arr[0][1];
        for (int i = 1; i < N; i++) {
            if (arr[i][1] > endTime) {
                if (arr[i][0] >= endTime) {
                    count++;
                    endTime = arr[i][1];
                }
            }

        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }
}