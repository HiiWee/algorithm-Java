package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathOfGameMap {

    // 단순 최단거리탐색
    int[] row = {-1, 0, 1, 0};
    int[] col = {0, 1, 0, -1};
    int[][] visitedCount;

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] maps) {
        visitedCount = new int[maps.length][maps[0].length];

        Queue<Node> que = new LinkedList<>();
        // 시작점은 0,0
        que.add(new Node(0, 0));
        visitedCount[0][0] = 1;

        while (!que.isEmpty()) {
            Node currentNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = currentNode.r + row[i];
                int nextC = currentNode.c + col[i];

                if (nextR >= maps.length || nextR < 0 || nextC >= maps[0].length || nextC < 0) {
                    continue;
                }
                if (visitedCount[nextR][nextC] > 0 || maps[nextR][nextC] == 0) {
                    continue;
                }
                visitedCount[nextR][nextC] = visitedCount[currentNode.r][currentNode.c] + 1;
                que.add(new Node(nextR, nextC));
            }
        }

        if (visitedCount[maps.length - 1][maps[0].length - 1] == 0) {
            return -1;
        }
        return visitedCount[maps.length - 1][maps[0].length - 1];
    }

}
