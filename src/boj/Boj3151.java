package boj;

import java.io.*;
import java.util.*;

class Boj3151 {
    static int[] arr;
    static int N;

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

        long count = 0;
        for (int i = 0; i < N; i++) {
            // 합이 0이 되려면 어차피 음수만 탐색하면 된다.
            if (arr[i] > 0) {
                break;
            }
            count += getCount(i);
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();

    }

    public static long getCount(int fixIdx) {
        int left = fixIdx + 1;
        int right = N - 1;
        long count = 0;

        while (left < right) {
            long sum = arr[left] + arr[right] + arr[fixIdx];

            if (sum == 0) {
                long sCount = 1;
                long eCount = 1;
                // fix된 수 말고 시작과 끝 수가 동일하다면 그 사이 수도 모두 동일 따라서 nC2로 값을 구하여 바로 값을 리턴하여 종료
                if (arr[left] == arr[right]) {
                    count += combination(right - left + 1);
                    return count;
                }
                // 현재 left값과 다음 값이 동일하다면 증가
                while (arr[left] == arr[left + 1]) {
                    left++;
                    sCount++;
                }
                // 현재 right 값과 이전 값이 동일하다면 감소
                while (arr[right] == arr[right - 1]) {
                    right--;
                    eCount++;
                }
                count += sCount * eCount;
            }
            // else if 가 아닌 if로 독릭접으로 처리한다. 그래야 바깥의 else 문을 만나 left == right가 되어 바깥 while문이 종료됨
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    private static long combination(int n) {
        return n * (n - 1) / 2;
    }
}
