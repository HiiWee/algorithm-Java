package boj;

import java.util.*;
import java.io.*;

/**
 * 유형: 이분탐색, 파라메트릭 서치
 */
class Boj13397 {
    static int[] arr;
    static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            high = Math.max(high, arr[i]);
        }

        int low = 0;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            int count = getCount(mid);

            // 만약 구간의 수가 m보다 작으면 mid값이 너무 크다는 의미이므로 high를 줄인다.
            // 같아도 high를 줄이는 이유는, 최대의 최소값을 찾아야 하므로 더 작은 값도 만족되는지 확인해보기 위함이다.
            if (count <= m) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        bw.write(low + "\n");
        bw.flush();
        bw.close();
    }

    public static int getCount(int mid) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int count = 1;  // 최초 구간은 전체이므로 1개
        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            // 정해진 mid값을 넘었으므로 구간을 나눔, 구간을 나누게 되면 현재 인덱스는 구간에 포함되지 않았으므로
            // 현재 인덱스부터 다시 탐색하기 위해 i--를 한다.
            if (max - min > mid) {
                count++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--;
            }
        }
        return count;
    }
}