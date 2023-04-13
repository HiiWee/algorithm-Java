package programmers;

import java.util.LinkedList;
import java.util.Queue;

// DFS, BFS
public class Network {

    boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                doBfs(i, computers);
                answer++;
            }
        }
        return answer;
    }

    public void doDfs(int startNode, int[][] computers) {
        visited[startNode] = true;
        for (int i = 0; i < computers.length; i++) {
            if (computers[startNode][i] != 0 && !visited[i] && startNode != i) {
                visited[i] = true;
                doDfs(i, computers);
            }
        }
    }

    public void doBfs(int startNode, int[][] computers) {
        Queue<Integer> que = new LinkedList<>();
        que.add(startNode);
        visited[startNode] = true;

        while (!que.isEmpty()) {
            int currentNode = que.poll();

            for (int i = 0; i < computers.length; i++) {
                // 이어져 있지 않거나, 방문한 노드라면 pass
                if (computers[currentNode][i] == 0 || visited[i]) {
                    continue;
                }
                visited[i] = true;
                que.offer(i);
            }
        }
    }
}
