package boj;

/*
    2행짜리 표를 방향 그래프로 보고 상위 행에서 하위 행으로 진출하는 간선이 존재한다고 가정해보자
    결국 뽑힌 정수들의 집합과 뽑힌 정수들 바로 아래에 있는 숫자들의 집합이 동일하기 위해서는
    참조의 시작과 끝이 동일한 루프(loop) 가 존재해야 한다.

    따라서 DFS, BFS로 루프를 구하고, 해당 루프의 시작과 끝이 동일한지 판별한다.
    동일하다면 지금까지 순회한 숫자들을 저장해야 하는데 탐색을 하는 중간에 계속 저장을 하다
    마지막에 판별 이후 담을지 말지 결정한다.
*/

import java.util.*;
import java.io.*;

// BFS 풀이
class Boj2668_1 {
    static List<Integer>[] adjList;
    static Set<Integer> resultSet = new TreeSet<>();
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(br.readLine());
            adjList[i].add(num);
        }

        for (int i = 1; i <= n; i++) {
            BFS(i);
        }
        bw.write(resultSet.size() + "\n");
        for (int result : resultSet) {
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();

    }

    public static void BFS(int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> que = new LinkedList<>();
        List<Integer> tempList = new ArrayList<>();
        que.add(start);
        tempList.add(start);

        while (!que.isEmpty()) {
            int current = que.poll();

            for (int next : adjList[current]) {
                if (start == next) {
                    resultSet.addAll(tempList);
                    break;
                }
                if (visited[next]) {
                    continue;
                }
                que.add(next);
                visited[next] = true;
                tempList.add(next);
            }
        }

    }
}

// DFS 풀이
class Boj2668_2 {
    static List<Integer>[] adjList;
    static Set<Integer> resultSet = new TreeSet<>();
    static List<Integer> tempList = new ArrayList<>();
    static boolean[] visited;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(br.readLine());
            adjList[i].add(num);
        }

        for (int i = 1; i <= n; i++) {
            DFS(i, i);
            visited = new boolean[n + 1];
            tempList = new ArrayList<>();
        }

        bw.write(resultSet.size() + "\n");
        for (int node : resultSet) {
            bw.write(node + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int start, int node) {
        visited[node] = true;
        tempList.add(node);
        for (int next : adjList[node]) {
            if (next == start) {
                resultSet.addAll(tempList);
                return;
            }
            if (!visited[next]) {
                DFS(start, next);
            }
        }
    }
}