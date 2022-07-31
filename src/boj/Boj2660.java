package boj;

/*
    문제의 조건에서 몇사람을 통하면 모두 서로를 알 수 있다 --> 모든 친구들은 이어져 있다라고 생각
    친구와 친구 사이의 거리에따라 가중치가 더해지고, 제일 먼 친구의 가중치가 해당 친구의 점수가 된다.

    모든 친구들은 이어져 있으므로 연결 그래프 구조인것을 알 수 있다.
    DFS, BFS를 이용하여 풀어보자
*/

import java.util.*;
import java.io.*;

class Boj2660 {
    static List<Integer> list = new ArrayList<>();
    static List<Integer>[] people;
    static int min = Integer.MAX_VALUE;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        people = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            people[i] = new ArrayList<>();
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if (num1 == -1 && num2 == -1) {
                break;
            }
            people[num1].add(num2);
            people[num2].add(num1);
        }
        for (int i = 1; i <= n; i++) {
            BFS(i);
        }
        Collections.sort(list);
        bw.write(min + " " + list.size() + "\n");
        for (int person : list) {
            bw.write(person + " ");
        }
        bw.flush();
        bw.close();

    }

    public static void BFS(int start) {
        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];
        Queue<Integer> que = new LinkedList<>();

        que.add(start);
        visited[start] = true;
        int max = 0;

        while (!que.isEmpty()) {
            int person = que.poll();

            for (int next : people[person]) {
                if (visited[next]) {
                    continue;
                }
                que.add(next);
                distance[next] = distance[person] + 1;
                visited[next] = true;
                max = Math.max(distance[next], max);
            }
        }
        update(start, max);
    }

    public static void update(int person, int max) {
        if (min > max) {
            min = max;
            list = new ArrayList<>();
            list.add(person);
        } else if (min == max) {
            list.add(person);
        }

    }
}