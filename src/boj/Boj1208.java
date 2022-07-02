package boj;

import java.util.*;
import java.io.*;

// 서로 다른 배열에서의 투 포인터 알고리즘으로 부분 수열을 구하기
class Boj1208_1 {
    static List<Integer> left = new ArrayList<>();
    static List<Integer> right = new ArrayList<>();
    static int[] arr;
    static int S = 0;

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

        // 배열을 반으로 쪼개 각각 모든 조합을 구해 더하여 나올 수 있는 합의 경우의 수를 두개로 나누어 저장
        sumList(0, N / 2, 0, left);
        sumList(N / 2, N, 0, right);

        Collections.sort(left);
        Collections.sort(right);

        long count = getCount();
        // S == 0 이면 위에서 구한 0 + 0 (공집합)인 경우를 빼줘야한다.
        if (S == 0) {
            count--;
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void sumList(int depth, int end, int sum, List<Integer> list) {
        if (depth == end) {
            list.add(sum);
            return;
        }
        sumList(depth + 1, end, sum + arr[depth], list);
        sumList(depth + 1, end, sum, list);
    }

    public static long getCount() {
        int pl = 0;
        int pr = right.size() - 1;
        long count = 0;
        while (pl < left.size() && pr >= 0) {
            int sum = left.get(pl) + right.get(pr);

            // 합과 S가 동일하다면
            if (sum == S) {
                long leftCount = 0;
                int leftValue = left.get(pl);
                // 현재 pl이 가리키는 값이 몇번 연속하는지 확인
                while (pl < left.size() && left.get(pl) == leftValue) {
                    pl++;
                    leftCount++;
                }
                long rightCount = 0;
                int rightValue = right.get(pr);
                // 현재 pr이 가리키는 값이 몇번 연속하는지 확인
                while (pr >= 0 && right.get(pr) == rightValue) {
                    pr--;
                    rightCount++;
                }

                // pl과 pr이 각각 2, 3번 증가했다면 나올 수 있는 조합은 2 * 3으로 6개 따라서 곱하고 더함
                count += leftCount * rightCount;
                // 합이 S보다 크다면 전체 합을 줄여야 하므로 pr을 감소시킨다.
            } else if (sum > S) {
                pr--;
                // S보다 작다면 전체 합을 늘리기 위해 pl 증가
            } else {
                pl++;
            }
        }
        return count;
    }
}

// HashMap을 이용해 직접 count값을 구할 수 있음
class Boj1280_2 {
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>();
    static int S;
    static long count;

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
        sumLeft(0, N / 2, 0);
        sumRight(N / 2, N, 0);

        if (S == 0) {
            count--;
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void sumLeft(int depth, int end, int sum) {
        if (depth == end) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }
        sumLeft(depth + 1, end, sum + arr[depth]);
        sumLeft(depth + 1, end, sum);
    }

    public static void sumRight(int depth, int end, int sum) {
        if (depth == end) {
            count += map.getOrDefault(S - sum, 0);
            return;
        }
        sumRight(depth + 1, end, sum + arr[depth]);
        sumRight(depth + 1, end, sum);
    }
}
