package boj;

import java.util.*;
import java.io.*;

class Main {
    static int size;
    static List<Integer> left = new ArrayList<>();
    static List<Integer> right = new ArrayList<>();
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] pizzaOne = new int[M];
        int[] pizzaTwo = new int[N];

        for (int i = 0; i < M; i++) {
            pizzaOne[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            pizzaTwo[i] = Integer.parseInt(br.readLine());
        }


        for (int i = 0; i < M; i++) {
            check = new boolean[M];
            check[i] = true;
            circularSum(pizzaOne[i], i, i + 1, check, pizzaOne, left);
        }
        for (int i = 0; i < N; i++) {
            check = new boolean[N];
            check[i] = true;
            circularSum(pizzaTwo[i], i, i + 1, check, pizzaTwo, right);
        }
        left.add(0);
        right.add(0);

        Collections.sort(left);
        Collections.sort(right);

        long count = getCount();

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    // 원형 큐에서 연속적은 부분합을 구하는 재귀
    public static void circularSum(int sum, int startIdx, int idx, boolean[] check, int[] arr, List<Integer> list) {
        if (idx == arr.length) {
            idx = 0;
        }
        list.add(sum);

        // idx != startIdx - 1은 인덱스가 0일때만 전체의 합을 추가하고, 나머지 인덱스에선 끝에서 한자리는 뺌
        // 계속 전체의 합을 추가하면 값들이 중복되므로 빼준다.
        if (!check[idx] && idx != startIdx - 1 && sum <= size) {
            check[idx] = true;
            circularSum(sum + arr[idx], startIdx, idx + 1, check, arr, list);
        }
    }

    public static long getCount() {
        int pl = 0;
        int pr = right.size() - 1;
        long count = 0;

        while (pl < left.size() && pr >= 0) {
            int sum = left.get(pl) + right.get(pr);

            if (sum == size) {

                int leftValue = left.get(pl);
                long leftCount = 0;
                while (pl < left.size() && left.get(pl) == leftValue) {
                    leftCount++;
                    pl++;
                }

                int rightValue = right.get(pr);
                long rightCount = 0;
                while (pr >= 0 && right.get(pr) == rightValue) {
                    rightCount++;
                    pr--;
                }

                count += leftCount * rightCount;
            } else if (sum < size) {
                pl++;
            } else {
                pr--;
            }
        }
        return count;
    }
}