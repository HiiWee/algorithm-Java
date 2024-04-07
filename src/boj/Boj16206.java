package boj;

import java.io.*;
import java.util.*;

class Boj16206 {

    static int n, m;
    static int[] cakes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cakes = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cakes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cakes);

        int cutCount = 0;
        int count = 0;
        // 10으로 나누어 떨어지되, 끝까지 자를때의 수치 + cutCount가 m을 초과하지 않는 경우를 먼저 자름
        for (int i = 0; i < n; i++) {
            if (cakes[i] % 10 == 0 && cakes[i] / 10 - 1 + cutCount <= m) {
                cutCount += cakes[i] / 10 - 1;
                count += cakes[i] / 10;
                cakes[i] = 1;
            }
        }

        if (cutCount < m) {
            for (int i = 0; i < n; i++) {
                if (cakes[i] > 10) {
                    int curCount = Math.min(cakes[i] / 10, m - cutCount);
                    cutCount += curCount;
                    count += curCount;

                    if (cutCount == m) {
                        break;
                    }
                }
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}
