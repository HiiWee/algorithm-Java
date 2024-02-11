package programmers;

import java.util.*;

class 전력망을_둘로_나누기 {

    int n;
    // A -> B or B -> A 간선 제외
    List<Integer>[] trees;
    int minDiff = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {
        this.n = n;
        trees = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }

        // 양방향 트리 구성
        for (int i = 0; i < wires.length; i++) {
            trees[wires[i][0]].add(wires[i][1]);
            trees[wires[i][1]].add(wires[i][0]);
        }

        // 임의의 노드 하나만 탐색해도 가능함
        dfs(1, new boolean[n + 1]);

        return minDiff;
    }

    public int dfs(int node, boolean[] visited) {
        visited[node] = true;

        // 자기자신의 개수
        int sum = 1;
        for (int next : trees[node]) {
            if (!visited[next]) {
                sum += dfs(next, visited);
            }
        }
        // node를 root라고 생각했을때 구한 sum의 값은 node를 포함한 하위 노드들의 갯수가 됩니다.
        // 따라서 모든 경우의 sum을 전부 고려하여 차를 계산하면 모든 노드가 루트노드일때 하위 노드들의 갯수를 구할 수 있으므로
        // 2개로 트리를 분리하는 모든 경우를 탐색할 수 있습니다.
        minDiff = Math.min(Math.abs(n - sum - sum), minDiff);
        return sum;
    }
}