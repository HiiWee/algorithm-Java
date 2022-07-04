package boj;

import java.util.*;
import java.io.*;

class Boj2143 {
    static int T;
    static List<Integer> left = new ArrayList<>();
    static List<Integer> right = new ArrayList<>();
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            check = new boolean[n];
            check[i] = true;
            cumulativeSum(A[i], i + 1, check, A, left);
        }
        for (int i = 0; i < m; i++) {
            check = new boolean[m];
            check[i] = true;
            cumulativeSum(B[i], i + 1, check, B, right);
        }

        Collections.sort(left);
        Collections.sort(right);

        long count = getCount();

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void cumulativeSum(int sum, int idx, boolean[] check, int[] arr, List<Integer> list) {
        if (idx == arr.length) {
            list.add(sum);
            return;
        }
        list.add(sum);

        if (!check[idx]) {
            check[idx] = true;
            cumulativeSum(sum + arr[idx], idx + 1, check, arr, list);
        }
    }

    public static long getCount() {
        int pl = 0;
        int pr = right.size() - 1;
        long count = 0;

        while (pl < left.size() && pr >= 0) {
            int leftValue = left.get(pl);
            int rightValue = right.get(pr);
            int sum = leftValue + rightValue;

            if (sum == T) {
                long leftCount = 0;
                while (pl < left.size() && left.get(pl) == leftValue) {
                    pl++;
                    leftCount++;
                }

                long rightCount = 0;
                while (pr >= 0 && right.get(pr) == rightValue) {
                    pr--;
                    rightCount++;
                }

                count += leftCount * rightCount;
            } else if (sum < T) {
                pl++;
            } else {
                pr--;
            }
        }
        return count;
    }
}
