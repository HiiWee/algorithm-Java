package algorithm.devmatching;

/*
    각 붙어있는 영토별로 BFS를 통해 알파벳을 카운팅해준다
    카운팅은 HashMap을 통해 카운팅하자
    하나의 영역을 모두 방문처리를 완료하면 제일 큰 세력을 찾는다.
    혹시라도 제일 큰 세력이 여러 알파벳일 수 있으므로 체크하고
    제일 큰 세력을 갖는 알파벳이 여러개라면 사전순으로 뒤에 위치한 세력을 고른다.
    이후 영토를 칠해주는데 자신보다 작은 영토만 흡수해야 한다.
*/
import java.util.*;
class Solution {
    char[][] map;
    boolean[][] visited;
    Map<Character, Integer> hashMap;
    int[] row = {-1, 0, 1, 0};
    int[] col = {0, 1, 0, -1};

    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(String[] maps) {
        int answer = 0;
        map = new char[maps.length][];
        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }
        visited = new boolean[map.length][map[0].length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!visited[i][j] && map[i][j] != '.') {
                    hashMap = new HashMap<>();
                    bfs(i, j);
                }
            }
        }

        return answer;
    }

    public void bfs(int r, int c) {
        Queue<Node> que = new LinkedList<>();

        que.add(new Node(r, c));
        visited[r][c] = true;

        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = row[i] + cur.r;
                int nextC = col[i] + cur.c;

                if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map[0].length) {
                    continue;
                }

                if (!visited[nextR][nextC] && map[nextR][nextC] != '.') {
                    que.add(new Node(nextR, nextC));
                    visited[nextR][nextC] = true;
                    hashMap.put(map[nextR][nextC], hashMap.getOrDefault(map[nextR][nextC], 0) + 1);
                }
            }
        }
    }
}
