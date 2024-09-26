package leetcode;

import java.util.*;

class LeetCode210 {

    List<Integer>[] graph;
    int[] indegree;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        graph = new ArrayList[numCourses];
        indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int [] edge : prerequisites) {
            indegree[edge[0]]++;
            graph[edge[1]].add(edge[0]);
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                que.offer(i);
            }
        }

        int index = 0;
        int[] answer = new int[numCourses];
        while (!que.isEmpty()) {
            int current = que.poll();
            answer[index++] = current;

            for (int next : graph[current]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] != 0) {
                return new int[0];
            }
        }

        return answer;
    }
}
