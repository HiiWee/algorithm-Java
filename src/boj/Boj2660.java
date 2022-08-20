package boj;

/*
    문제의 조건에서 몇사람을 통하면 모두 서로를 알 수 있다 --> 모든 친구들은 이어져 있다라고 생각
    친구와 친구 사이의 거리에따라 가중치가 더해지고, 제일 먼 친구의 가중치가 해당 친구의 점수가 된다.

    모든 친구들은 이어져 있으므로 연결 그래프 구조인것을 알 수 있다.
    DFS, BFS를 이용하여 풀어보자
*/

import java.util.*;
import java.io.*;

class Boj2660_1 {
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

/*
    플로이드 워셜로 풀어보자
*/
class Boj2660_2 {
    public static final int INF = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] relation = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(relation[i], INF);
            relation[i][i] = 0;
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start == -1) {
                break;
            }
            relation[start][end] = 1;
            relation[end][start] = 1;
        }

        for (int middle = 1; middle <= n; middle++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (relation[from][to] > relation[from][middle] + relation[middle][to]) {
                        relation[from][to] = relation[from][middle] + relation[middle][to];
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        List<Integer> persons = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= n; j++) {
                max = Math.max(max, relation[i][j]);
            }
            if (min > max) {
                min = max;
                persons = new ArrayList<>();
                persons.add(i);
            } else if (min == max) {
                persons.add(i);
            }
        }

        Collections.sort(persons);
        bw.write(min + " " + persons.size() + "\n");
        for (int person : persons) {
            bw.write(person + " ");
        }
        bw.flush();
        bw.close();
    }
}