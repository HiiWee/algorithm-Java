package boj;

/*
    기존 2141 우체국과 동일한 문제
*/
import java.io.*;
import java.util.*;

class Boj2285 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        long total = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            total += arr[i][1];
        }
        Arrays.sort(arr, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        long sum = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i][1];
            if (sum >= (total + 1) / 2) {
                index = i;
                break;
            }
        }

        bw.write(arr[index][0] + "\n");
        bw.flush();
        bw.close();
    }
}