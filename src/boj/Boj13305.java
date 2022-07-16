package boj;

/*
    1. 현재 주요소와 다음 주유소의 리터당 가격을 비교한다.
        - 현재 주유소가 더 비싸다면, 현재 주유소에서 다음 주유소로 갈 수 있는 금액만 구매
        - 현재 주요소가 더 싸다면, 다음 주유소에서 다음 다음 주유소로 갈 수 있는 금액까지 구매하고,
          또 그 다음다음다음 주유소와도 비교한다(반복문) 그러다가 비싼 주유소를 만나면 정지
          여기서 바깥 for문의 인덱스를 현재 for문에서 탐색한 인덱스로 교체해줘야 함

       - 위의 말이 결국 각 주유소의 리터당 기름값이 내림차순이면 그대로 구매하지만 그렇지 않은경우
         직전도시에서 기름을구매하면 된다.
         주유소 가격이 8 9 7 10 2 3 -> 8 8 7 7 2 2로 가격을 책정하고 거리값을 곱하면 된다.
*/

import java.io.*;
import java.util.StringTokenizer;

// Greedy
class Boj13305 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[] price = new long[n];
        long[] distance = new long[n - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            price[i] = Long.parseLong(st.nextToken());
        }

        long total = 0;
        for (int i = 0; i < n - 1; i++) {
            if (price[i] < price[i + 1]) {
                price[i + 1] = price[i];
            }
            total += price[i] * distance[i];
        }
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
