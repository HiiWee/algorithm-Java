package boj;

import java.util.*;
import java.io.*;

class Boj19637 {
    static String[] title;
    static int[] power;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        title = new String[N];
        power = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(title[lowerBound(num)]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    public static int lowerBound(int num) {
        int low = 0;
        int high = N - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (power[mid] >= num) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
