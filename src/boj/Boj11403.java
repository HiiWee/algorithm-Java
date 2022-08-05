package boj;

/*
    방향 그래프 이다. 전체적으로 0 ~ n - 1 까지 순회하며
    해당 번호를 기준점으로 잡고 BFS를 시작한다.

    만약 초기 n값이 0이고, 0에서 갈 수 있는 점을 모두 탐색하기 위해서는 다음같은 상황을 모두 담아야한다.
    0 -> 1 -> 2 라면 0 -> 1로 갈 수 있고, 0 -> 2로도 갈 수 있다.
    따라서 기준점인 0을 고정으로 두고 아직 방문하지 않고, 해당 노드로의 간선이 연속적으로 존재하는 노드들을 계속 탐색하고 해당 노드들이 연속적이라면
    [0][연속된 노드 번호]의 위치는 1로 업데이트한다. 이후 해당 노드번호를 큐에담고 0부터 연속적으로 이어지는 모든 노드들을 방문할때까지 반복한다.

    이게 끝나면 다음은 1에서 갈 수 있는 모든 점을 위와 동일하게 탐색하기 시작한다.
*/

import java.io.*;
import java.util.*;

class Boj11403 {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        for (int i = 0; i < n; i++) {
            Queue<Integer> que = new LinkedList<>();
            boolean[] visited = new boolean[n];
            que.offer(i);

            while (!que.isEmpty()) {
                int current = que.poll();

                for (int j = 0; j < n; j++) {
                    if (arr[current][j] == 1 && !visited[j]) {
                        que.offer(j);
                        visited[j] = true;
                        arr[i][j] = 1;
                    }
                }
            }
        }
    }
}