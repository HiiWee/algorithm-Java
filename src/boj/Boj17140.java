package boj;

/*
    한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는지 알아야 한다.
    그 다음, 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬한다.
    그 다음에는 배열 A에 정렬된 결과를 다시 넣어야 한다.
    정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.

    1. 각각의 숫자가 몇개 들어있는지 카운팅함
    2. 정렬함
        - 수의 등장 횟수 기준으로 정렬하되, 동일한 등장횟수면 수가 커지는 순서로 정렬
*/
import java.io.*;
import java.util.*;

class Boj17140 {

    private static int r, c, k;
    private static int rowSize = 3, colSize = 3;
    private static int[][] map = new int[100][100];
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int count, number;

        Node(int count, int number) {
            this.count = count;
            this.number = number;
        }

        @Override
        public int compareTo(final Node o) {
            if (count == o.count) {
                return number - o.number;
            }
            return count - o.count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int tryCount = 0;
        while (tryCount <= 100) {
            if (map[r - 1][c - 1] == k) {
                bw.write(tryCount + "");
                bw.flush();
                bw.close();
                return;
            }
            if (rowSize >= colSize) {
                for (int i = 0; i < rowSize; i++) {
                    calcR(i);
                }
            } else {
                for (int i = 0; i < colSize; i++) {
                    calcC(i);
                }
            }
            tryCount++;
        }

        bw.write("-1");
        bw.flush();
        bw.close();
    }

    private static void calcC(final int colNumber) {
        int maxNumber = 0;
        int[] numberCount = new int[101];

        for (int i = 0; i < rowSize; i++) {
            numberCount[map[i][colNumber]]++;
            maxNumber = Math.max(maxNumber, map[i][colNumber]);
        }
        for (int i = 1; i <= maxNumber; i++) {
            if (numberCount[i] > 0) {
                pq.offer(new Node(numberCount[i], i));
            }
        }
        int index = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            map[index++][colNumber] = node.number;
            map[index++][colNumber] = node.count;
        }
        rowSize = Math.max(rowSize, Math.min(100, index));

        for (int i = index; i < 100; i++) {
            map[i][colNumber] = 0;
        }
    }

    public static void calcR(int rowNumber) {
        int maxNumber = 0;
        int[] numberCount = new int[101];
        // 나오는 숫자들의 개수를 카운팅
        for (int i = 0; i < colSize; i++) {
            numberCount[map[rowNumber][i]]++;
            maxNumber = Math.max(maxNumber, map[rowNumber][i]);
        }
        // 숫자 및 카운팅을 Node객체로 감싸서 우선순위큐에 삽입
        for (int i = 1; i <= maxNumber; i++) {
            if (numberCount[i] > 0) {
                pq.offer(new Node(numberCount[i], i));
            }
        }
        int index = 0;
        // 배열에 기록
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            map[rowNumber][index++] = node.number;
            map[rowNumber][index++] = node.count;
        }
        colSize = Math.max(colSize, Math.min(100, index));

        // 나머지 값들은 0으로 초기화
        for (int i = index; i < 100; i++) {
            map[rowNumber][i] = 0;
        }
    }
}