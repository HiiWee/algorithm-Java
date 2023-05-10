package boj;

/*
    - 우선순위 큐 2개를 생성한다.
      한 개는 최소힙, 다른 하나는 최대힙을 갖는다.

    -1번째 값은 일단 최소힙에 넣는다.
    -우선 1번쨰 값을 중앙값으로 받으므로, 이후에 들어오는 숫자를 현재 중앙값과 비교해 크다면 최소힙으로, 작거나 같다면 최대힙으로 offer한다.

    - 홀수 자릿수에 도착하는 경우
      최대힙의 원소가 최소힙의 원소보다 1개 많은 경우일때 최대힙의 peek값이 항상 중앙값이 된다.
      ex) 최소힙 [20, 30, 40], 최대힙 [14, 15, 16, 17]  힙 내용이 다음과 같을때 중앙값은 항상 최대힙의 peek값이 된다.
      이 말의 의미는 곧 최대힙의 원소 개수 == 현재 중앙값의 위치(현재 홀수 값 / 2 + 1)과 같다.
      위의 예시를 계산해보면 최대힙의 원소개수(4) == 현재 중앙값의 위치(7 / 2 + 1)과 같다.

      따라서 중앙값의 위치 값보다 최대힙의 개수가 많다면 그 차이만큼 최대힙 -> 최소힙으로 이동시키고,
      중앙값의 위치 값보다 최대힙의 개수가 적다면 그 차이만큼 최소힙 -> 최대힙으로 이동시킨다.

    - 위치 조정이 완료됐다면 최대힙의 peek 값을 중앙값이 되므로 results에 포함시킨다.
      그리고 중앙값을 갱신하여 다음 값을 탐색할때 사용한다.
*/

import java.io.*;
import java.util.*;

class Boj2696 {
    private static final int OFFSET = 10;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());

        List<List<Integer>> totalResults = new ArrayList<>();

        while (testCase-- > 0) {
            int m = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            List<Integer> results = new ArrayList<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();

            int middleNumber = Integer.parseInt(st.nextToken());
            maxHeap.offer(middleNumber);
            results.add(middleNumber);

            for (int i = 2; i <= m; i++) {
                int number = Integer.parseInt(st.nextToken());

                // 10개 넘으면 다음 줄을 받아야 한다.
                if (i % OFFSET == 0) {
                    st = new StringTokenizer(br.readLine());
                }

                if (middleNumber < number) {
                    minHeap.offer(number);
                } else {
                    maxHeap.offer(number);
                }

                if (i % 2 == 1) {
                    int rightPosition = i / 2 + 1;
                    while (maxHeap.size() > rightPosition) {
                        minHeap.offer(maxHeap.poll());
                    }
                    while (maxHeap.size() < rightPosition) {
                        maxHeap.offer(minHeap.poll());
                    }

                    middleNumber = maxHeap.peek();
                    results.add(middleNumber);
                }
            }
            totalResults.add(results);
        }

        StringBuilder totalResultsBuilder = new StringBuilder();
        for (List<Integer> results : totalResults) {
            totalResultsBuilder.append(results.size()).append("\n");
            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i < results.size(); i++) {
                if (i % (OFFSET - 1) == 0) {
                    resultBuilder.append(results.get(i)).append("\n");
                    continue;
                }
                resultBuilder.append(results.get(i)).append(" ");
            }
            totalResultsBuilder.append(resultBuilder.toString().trim()).append("\n");
        }

        bw.write(totalResultsBuilder.toString());
        bw.flush();
        bw.close();
    }
}