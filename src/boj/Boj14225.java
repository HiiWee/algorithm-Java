package boj;

/*
    1 ~ n개를 뽑는 조합을 모두 구하고, 각 조합들의 합을 모두 저장한 뒤 1부터 2백만까지 카운팅하며 없는 최소 숫자를 고른다.
*/

import java.io.*;
import java.util.*;

class Boj14225 {
    static int n;
    static int[] arr;
    static boolean[] visited = new boolean[2000001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        allCombination(0, 0);

        int num = 0;
        for (int i = 1; i <= 2000000; i++) {
            if (!visited[i]) {
                num = i;
                break;
            }
        }

        bw.write(num + "\n");
        bw.flush();
        bw.close();
    }

    public static void allCombination(int depth, int sum) {
        if (depth == n) {
            visited[sum] = true;
            return;
        }

        allCombination(depth + 1, sum + arr[depth]);
        allCombination(depth + 1, sum);
    }
}