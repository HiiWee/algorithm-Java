package programmers;

import java.util.*;

class 양과_늑대 {

    int[] info;
    int[] visited = new int[1 << 17];
    List<Integer>[] trees;
    int max;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        trees = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            trees[i] = new ArrayList<>(2);
        }

        for (int[] edge : edges) {
            trees[edge[0]].add(edge[1]);
        }

        visited[1] = 1;
        search(1);

        return max;
    }

    public void search(int state) {
        int total = 0;
        int wolf = 0;
        for (int i = 0; i < info.length; i++) {
            if ((state & (1 << i)) != 0) {
                total++;
                wolf += info[i];
            }
        }
        if (wolf * 2 >= total) {
            return;
        }
        max = Math.max(total - wolf, max);

        for (int i = 0; i < info.length; i++) {
            if ((state & (1 << i)) != 0) {
                for (int next : trees[i]) {
                    if ((state & (1 << next)) == 0 && visited[state | (1 << next)] != 1) {
                        visited[state | (1 << next)] = 1;
                        search(state | (1 << next));
                    }
                }
            }
        }
    }
}