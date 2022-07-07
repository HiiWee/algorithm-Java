package boj;

import java.io.*;
import java.util.*;

class Boj3079 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        long minTime = lowerBound();
        bw.write(minTime + "\n");
        bw.flush();
        bw.close();
    }

    public static long lowerBound() {
        // lower bound 이므로 최소값 1보다 한 칸 더 내려간 후 다시 1로 low가 증가하므로
        // 한 칸 내린 값을 low 값으로 둠
        long low = 0;
        long high = Long.MAX_VALUE;
        long mid;
        while (low < high) {
            mid = (low + high) / 2;

            long total = 0;
            for (int time : arr) {
                total += mid / time;
                // 모든 사람 검사 가능시, 루프 탈출
                if (total >= M) {
                    break;
                }
            }

            if (total >= M) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}