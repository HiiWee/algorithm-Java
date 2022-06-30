package boj;

import java.util.*;
import java.io.*;

// 조합을 이용해 정석으로 구현
class Boj1182_1 {
    static int[] arr;
    static int count;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            combination(new boolean[N], 0, N, i);
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void combination(boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            countSumOfArr(visited);
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static void countSumOfArr(boolean[] visited) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                sum += arr[i];
            }
        }
        if (sum == S) {
            count++;
        }
    }
}

// 백트래킹만 이용해 굳이 조합 전부를 구현하지 않고, 단지 더하여 합을 구하는 기능에 집중해서 구현
class Boj1182_2 {
    static int[] arr;
    static int count;
    static int S, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        sumBacktracking(0, 0);
        
        // 맨 처음 아무것도 선택하지 않게되면 0이므로 이때 count++한것을 빼줌
        if (S == 0) {
            count--;
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void sumBacktracking(int depth, int sum) {
        if (depth == N) {
            if (sum == S) {
                count++;
            }
            return;
        }

        sumBacktracking(depth + 1, sum + arr[depth]);
        sumBacktracking(depth + 1, sum);

    }

}