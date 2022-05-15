package boj;

import java.io.*;
import java.util.StringTokenizer;

class Boj9613 {
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String[] nums = br.readLine().split(" ");
            sb = new StringBuilder();
            visited = new boolean[nums.length - 1];
            combi(nums, 0, 2);
            long sum = sumGCD();
            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static long sumGCD() {
        long sum = 0;
        String[] pairs = sb.toString().split(",");
        for (int i = 0; i < pairs.length; i++) {
            String[] nums = pairs[i].split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            if (a > b) {
                sum += GCD(a, b);
            } else {
                sum += GCD(b, a);
            }
        }
        return sum;
    }

    public static int GCD(int a, int b) {
        int result = a % b;
        if (result == 0) {
            return b;
        }
        return GCD(b, result);
    }


    public static void combi(String[] nums, int depth, int r) {
        if (r == 0) {
            put(nums);
            return;
        }
        if (depth == Integer.parseInt(nums[0])) {
            return;
        }
        visited[depth] = true;
        combi(nums, depth + 1, r - 1);
        visited[depth] = false;
        combi(nums, depth + 1, r);
    }

    public static void put(String[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (visited[i - 1]) {
                sb.append(nums[i]).append(" ");
            }
        }
        sb.append(",");
    }

}
