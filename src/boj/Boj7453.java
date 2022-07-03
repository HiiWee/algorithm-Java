package boj;

/*
    1. 먼저 AB 배열, CD배열의 조합의 합을 모두 구하고
    2. AB 조합의 합 배열, CD 조합의 합 배열을 이용해 다시 합들을 투포인터로 돌며 카운트한다.
*/

import java.util.*;
import java.io.*;

class Boj7453 {
    static int[] left;
    static int[] right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        left = new int[N * N];
        right = new int[N * N];
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                left[index] = A[i] + B[j];
                right[index] = C[i] + D[j];
                index++;
            }
        }

        Arrays.sort(left);
        Arrays.sort(right);

        long count = getCount();

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static long getCount() {
        int pl = 0;
        int pr = right.length - 1;
        long count = 0;
        while (pl < left.length && pr >= 0) {
            int sum = left[pl] + right[pr];

            if (sum == 0) {
                long leftCount = 0;
                int leftValue = left[pl];
                while (pl < left.length && left[pl] == leftValue) {
                    pl++;
                    leftCount++;
                }

                long rightCount = 0;
                int rightValue = right[pr];
                while (pr >= 0 && right[pr] == rightValue) {
                    pr--;
                    rightCount++;
                }

                count += leftCount * rightCount;
            } else if (sum < 0) {
                pl++;
            } else {
                pr--;
            }

        }
        return count;
    }

}