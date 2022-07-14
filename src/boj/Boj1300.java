package boj;

import java.io.*;

class Boj1300 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // mid는 배열 B[index]의 값
        // 따라서 mid를 이용해 mid의 값보다 작거나 같은 값들의 개수를 구하면 그 값이 k값과 비교할 수 있음
        // 그 이유는 B배열은 오름차순으로 정렬되어있는 B[k] = mid일때 mid값보다 작거나 같은 값이
        // k개 라는 의미이기 때문이다.

        int low = 1;
        int high = k;
        while (low < high) {
            int mid = (low + high) / 2;

            // 특정 수 보다 작거나 같은 값을 구하기 위해선
            // 해당 수 / 인덱스, n값 중 더 작은 값을 더해가면 특정 수 보다 작거나 같은 값을 구할 수 있음
            // 구구단에서 8보다 작거나 같은 수를 구하는것을 예제로 생각해보자
            int count = 0;
            for (int i = 1; i <= n; i++) {
                count += Math.min(mid / i, n);
            }

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.write(low + "\n");
        bw.flush();
        bw.close();
    }
}