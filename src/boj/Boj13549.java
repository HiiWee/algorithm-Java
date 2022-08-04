package boj;

/*
    0-1 BFS: O(V + E)로 선형적인 시간복잡도내에 탐색을 완료할 수 있다.
*/

import java.util.*;
import java.io.*;

class Boj13549_1 {
    static int[] arr = new int[100001];
    static int n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(arr, -1);
        BFS();

        bw.write(arr[k] + "\n");
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        Deque<Integer> deq = new LinkedList<>();
        deq.offer(n);
        arr[n] = 0;
        while (!deq.isEmpty()) {
            int current = deq.poll();

            if (current == k) {
                return;
            }

            if (current * 2 <= 100000 && arr[current * 2] == -1) {
                deq.offerFirst(current * 2);
                arr[current * 2] = arr[current];
            }
            if (current + 1 <= 100000 && arr[current + 1] == -1) {
                deq.offerLast(current + 1);
                arr[current + 1] = arr[current] + 1;
            }
            if (current - 1 >= 0 && current <= 100000 && arr[current - 1] == -1) {
                deq.offerLast(current - 1);
                arr[current - 1] = arr[current] + 1;
            }
        }
    }
}

/*
    0-1BFS가 아닌 Naive한 Dijkstra로 풀어보자
*/
class Boj13549_2 {
    static int n, k;
    static int[] dist = new int[100001];

    static class Node {
        int idx, cost;
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        que.offer(new Node(n, 0));

        while (!que.isEmpty()) {
            Node currentNode = que.poll();

            if (currentNode.idx == k) {
                break;
            }
            if (currentNode.cost > dist[currentNode.idx]) {
                continue;
            }

            if (currentNode.idx * 2 <= 100000) {
                if (dist[currentNode.idx * 2] > currentNode.cost) {
                    dist[currentNode.idx * 2] = dist[currentNode.idx];
                    que.offer(new Node(currentNode.idx * 2, dist[currentNode.idx * 2]));
                }
            }
            if (currentNode.idx + 1 < 100000) {
                if (dist[currentNode.idx + 1] > currentNode.cost + 1) {
                    dist[currentNode.idx + 1] = dist[currentNode.idx] + 1;
                    que.offer(new Node(currentNode.idx + 1, dist[currentNode.idx + 1]));
                }
            }
            if (currentNode.idx - 1 >= 0 && currentNode.idx <= 100000) {
                if (dist[currentNode.idx - 1] > currentNode.cost + 1) {
                    dist[currentNode.idx - 1] = dist[currentNode.idx] + 1;
                    que.offer(new Node(currentNode.idx - 1, dist[currentNode.idx - 1]));
                }
            }
        }
        bw.write(dist[k] + "\n");
        bw.flush();
        bw.close();
    }
}