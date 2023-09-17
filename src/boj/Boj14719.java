package boj;

import java.io.*;
import java.util.*;

class Boj14719 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] arr = new int[w];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (w <= 2) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }

        int sum = 0;
        int curTop = arr[0];
        int curTopIndex = 0;
        for (int i = 1; i < w; i++) {
            if (arr[i] > arr[i - 1]) {
                int fillHeight = Math.min(curTop, arr[i]);
                for (int j = curTopIndex + 1; j < i; j++) {
                    int needWater = fillHeight - arr[j];
                    if (needWater > 0) {
                        sum += needWater;
                        arr[j] = fillHeight;
                    }
                }

                if (arr[i] >= curTop) {
                    curTop = arr[i];
                    curTopIndex = i;
                }
            }
        }

        bw.write(sum + "");
        bw.flush();
        bw.close();
    }
}
