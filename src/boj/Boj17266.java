package boj;

import java.io.*;
import java.util.*;

class Boj17266 {

    static int n, m;
    static int[] lights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        lights = new int[m];

        for (int i = 0; i < m; i++) {
            lights[i] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = n;

        while (l < r) {
            int mid = (l + r) / 2;

            if (isPossible(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        bw.write(Integer.toString(l));
        bw.flush();
        bw.close();
    }

    public static boolean isPossible(int height) {
        int prev = 0;

        for (int light : lights) {
            int left = light - height;
            int right = light + height;

            if (left > prev) {
                return false;
            }
            prev = right;
        }

        return prev >= n;
    }
}

