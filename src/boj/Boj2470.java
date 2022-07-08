package boj;

import java.util.*;
import java.io.*;

class Boj2470 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] list = getTwoSolution();
        Arrays.sort(list);

        bw.write(list[0] + " " + list[1] + "\n");
        bw.flush();
        bw.close();
    }

    public static int[] getTwoSolution() {
        int l = 0;
        int r = N - 1;
        int min = Integer.MAX_VALUE;
        int[] solution = new int[2];

        // 서로 교차하게 되면 동일 수를 더할 수 있으므로 교차 직전까지만 탐색
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (min > Math.abs(sum)) {
                solution[0] = arr[l];
                solution[1] = arr[r];
                min = Math.abs(sum);
            }

            if (sum < 0) {
                l++;
            } else if (sum > 0) {
                r--;
            } else {
                // 0인 경우 더이상 작은 값이 없으므로 반환
                return solution;
            }

        }
        return solution;
    }
}