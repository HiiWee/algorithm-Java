package boj;

/*
    덱의 양쪽에서 삽입, 꺼낼 수 있는 원리를 이용해보자
    3 2 1 -3 -1
    -3 -1 2 1

    1번 풍선 뽑음 : 3칸오른쪽
    덱의 왼쪽에서 3번뽑고 오른쪽에 삽입
    -1 2 1 -3
    다음에 뽑을때는 우측에서 뽑음

    4번 풍선 뽑음: 3칸 왼쪽
    덱의 우측에서 3번뽑고 왼쪽에 삽입
    -1 2 1
    왼쪽에 삽입했으므로 다음에 뽑을때는 좌측에서 뽑음

    5번 풍선 뽑음: 1칸 왼쪽
    덱의 우측에서 1번뽑아 왼쪽에 삽입
    1 2
    왼쪽에 삽입했으므로 다음에 뽑을때는 좌측에서 뽑음

    3번풍선 뽑음: 1칸 오른쪽
    2
    우측에 삽입했으므로 다음에는 우측에서 뽑는다.

    2번풍선 뽑음 -> 덱이 비었으므로 종료
*/

import java.io.*;
import java.util.*;

class Boj2346 {
    static int[] arr;
    static Deque<Integer> deq = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        deq = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            deq.add(i);
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        while (deq.size() > 1) {
            int node;
            // 왼쪽에서 뽑음
            if (flag) {
                node = deq.pollFirst();
            } else {
                node = deq.pollLast();
            }
            flag = move(node);
            sb.append(node + 1).append(" ");
        }

        sb.append(deq.poll() + 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean move(int node) {
        if (arr[node] > 0) {
            for (int i = 0; i < arr[node]; i++) {
                deq.offerLast(deq.pollFirst());
            }
            return false;
        } else {
            for (int i = arr[node]; i < 0; i++) {
                deq.offerFirst(deq.pollLast());
            }
            return true;
        }
    }
}