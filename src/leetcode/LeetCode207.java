package leetcode;

import java.util.*;

class LeetCode207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] inDegree = new int[numCourses];

        for (int[] value : prerequisites) {
            inDegree[value[0]]++;
            graph[value[1]].add(value[0]);
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                que.offer(i);
            }
        }
        int count = 0;
        while (!que.isEmpty()) {
            int course = que.poll();
            count++;
            for (int nextCourse : graph[course]) {
                inDegree[nextCourse]--;

                if (inDegree[nextCourse] == 0) {
                    que.offer(nextCourse);
                }
            }
        }

        if (count != numCourses) {
            return false;
        }
        return true;
    }
}
