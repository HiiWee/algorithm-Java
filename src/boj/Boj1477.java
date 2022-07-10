package boj;

import java.util.*;
import java.io.*;

class Boj1477 {
    static int n, m, l;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n + 2];
        arr[0] = 0;
        arr[n + 1] = l;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int low = 1;
        int high = l;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            int count = 0;
            for (int i = 1; i < arr.length; i++) {
                // 기존 휴게소 사이의 거리에 신규 휴게소 사이의 거리를 이용하면 몇개의 휴개소를 지을 수 있을지 계산
                // 200 ~ 701 사이의 지을 수 있는 위치는 201 ~ 700 이므로 1을 뺌
                int distance = arr[i] - arr[i - 1] - 1;
                count += distance / mid;
            }
            // 만약 같거나 m값이 더 크다면 high값을 줄인다.
            if (count <= m) {
                high = mid;
                // 구한 값이 더 크다면 low를 증가
            } else {
                low = mid + 1;
            }
        }
        bw.write(low + "\n");
        bw.flush();
        bw.close();
    }
}