package boj;

import java.util.*;
import java.io.*;

class Boj11663 {
    static int N, M;
    static int[] point;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        point = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(point);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append((getUpperBound(end) - getLowerBound(start))).append("\n");

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int getLowerBound(int num) {
        int low = 0;
        int high = N;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (point[mid] >= num) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int getUpperBound(int num) {
        int low = 0;
        int high = N;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (point[mid] > num) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}