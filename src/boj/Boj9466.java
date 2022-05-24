package boj;

import java.io.*;
import java.util.*;

/*
     이미 방문한 노드라는 뜻은 싸이클을 만났다는 의미
     만약 싸이클을 만나고 현재 노드가 a이고 다음과같이 싸이클이 존재할때(a -> b -> c -> a)
     a 부터 c까지 visited를 하고 다시 a를 만나면 47line의 else if로 넘어간다.
     현재 root노드는 c이고 다시 c를 만날때까지 a -> b 를 방문하여 count한다. (c도 count하기 위해 초기에 1을 증가하고 loop을 돔)
     루프가 종료되면 현재 root노드인 c 부터 재귀함수 역순으로 c -> b -> a가 finished처리 된다.
     최종적으로 전체 노드수에서 사이클이 포함된 cycleCount를 제외하면 결과가 출력된다.
*/
class Boj9466 {
    static int[] graph;
    static boolean[] visited;
    static boolean[] finished;
    static int cycleCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            int T = Integer.parseInt(br.readLine());
            graph = new int[T + 1];
            visited = new boolean[T + 1];
            finished = new boolean[T + 1];
            cycleCount = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= T; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= T; i++) {
                DFS(i);
            }
            bw.write((T - cycleCount) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void DFS(int root) {
        if (visited[root]) {
            return;
        }
        visited[root] = true;
        int node = graph[root];
        if (!visited[node]) {
            DFS(node);
        } else if (!finished[node]) {
            cycleCount++;
            for (int i = node; i != root; i = graph[i]) {
                cycleCount++;
            }
        }
        finished[root] = true;
    }
}