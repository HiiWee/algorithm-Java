package boj;

import java.io.*;
import java.util.*;

// 이분탐색 풀이, lower, upper바운드의 결과 인덱스가 1 인덱스씩 줄어든것이 아쉬움
class Boj10816_1 {
    static int[] deck;
    static int[] selectDeck;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        int n = Integer.parseInt(br.readLine());
        deck = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            deck[i] = Integer.parseInt(st.nextToken());
            set.add(deck[i]);
        }

        int selectCard = Integer.parseInt(br.readLine());
        selectDeck = new int[selectCard];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < selectCard; i++) {
            selectDeck[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(deck);
        for (int i = 0; i < deck.length; i++) {
            System.out.print(deck[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < selectCard; i++) {

            int lowerNum = lowerBound(i);
            System.out.println(lowerNum);
            int upperNum = upperBound(i);
            System.out.println(upperNum);
            sb.append(upperNum - lowerNum).append(" ");
        }
        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
    }

    public static int lowerBound(int i) {
        int low = 0;
        int high = deck.length - 1;
        int mid;
        int targetNum = selectDeck[i];

        while (low <= high) {
            mid = (low + high) / 2;
            if (deck[mid] >= targetNum) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static int upperBound(int i) {
        int low = 0;
        int high = deck.length - 1;
        int mid;
        int targetNum = selectDeck[i];

        while (low <= high) {
            mid = (low + high) / 2;
            if (deck[mid] > targetNum) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

// 블로그 풀이 방식 lower, upper의 인덱스가 더 정확하다.
class Boj10816_2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken());

            // upperBound와 lowerBound의 차이 값을 구한다.
            sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(' ');
        }
        System.out.println(sb);
    }

    private static int lowerBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *
             *  (중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.)
             */
            if (key <= arr[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }

        }
        System.out.println(lo);
        return lo;
    }

    private static int upperBound(int[] arr, int key) {
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {

            int mid = (lo + hi) / 2; // 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) {
                hi = mid;
            }
            // 중복원소의 경우 else에서 처리된다.
            else {
                lo = mid + 1;
            }

        }

        System.out.println(lo);
        return lo;
    }


}
