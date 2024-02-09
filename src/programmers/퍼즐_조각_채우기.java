package programmers;

import java.util.*;

class 퍼즐_조각_채우기 {

    static class Node implements Comparable<Node> {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node node) {
            if (r == node.r) {
                return c - node.c;
            }
            return r - node.r;
        }

        @Override
        public String toString() {
            return "[" + r + ", " + c + "]";
        }
    }

    final int[] rows = {1, 0, -1, 0};
    final int[] cols = {0, 1, 0, -1};

    int n;
    int[][] board;
    int[][] table;
    int answer;

    List<List<Node>> areas = new ArrayList<>();
    List<List<Node>> puzzles = new ArrayList<>();

    public int solution(int[][] game_board, int[][] t) {
        n = t.length;
        board = game_board;
        table = t;

        boolean[][] puzzleVisited = new boolean[n][n];
        boolean[][] emptyVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!puzzleVisited[i][j] && table[i][j] == 1) {
                    puzzles.add(bfs(i, j, puzzleVisited, table, 1));
                }
                if (!emptyVisited[i][j] && board[i][j] == 0) {
                    areas.add(bfs(i, j, emptyVisited, board, 0));
                }
            }
        }

        matchPuzzleToBoard();

        return answer;
    }

    // 퍼즐을 하나씩 고르면서, 보드에 맞는 위치가 있는지 확인해야함
    // 고른 퍼즐은 회전을 시키면서 매칭을 시켜줘야 함
    // 맞다면 해당 보드는 방문처리 및 칸의 수를 더해줘야함
    public void matchPuzzleToBoard() {

        boolean[] visited = new boolean[areas.size()];

        for (List<Node> puzzle : puzzles) {
            for (int i = 0; i < areas.size(); i++) {
                // 조각과 채울 공간의 크기가 다르거나, 이미 처리한 조각이면 건너뜀
                if (puzzle.size() != areas.get(i).size() || visited[i]) {
                    continue;
                }

                // 현재 퍼즐이 맞아떨어지는 위치를 찾으면 다음 퍼즐을 탐색
                if (isMatched(puzzle, areas.get(i))) {
                    visited[i] = true;
                    answer += puzzle.size();
                    break;
                }
            }
        }
    }

    public boolean isMatched(List<Node> puzzle, List<Node> area) {
        for (int i = 0; i < 4; i++) {
            boolean isMatching = true;

            // 회전했으면 더 작은행들중 더 작은열을 가진 점을 기준으로 원점 이동을 해줘야함
            int firstR = puzzle.get(0).r;
            int firstC = puzzle.get(0).c;
            for (Node node : puzzle) {
                node.r -= firstR;
                node.c -= firstC;
            }

            // 퍼즐과 빈칸을 매칭함
            for (int j = 0; j < puzzle.size(); j++) {
                Node n1 = puzzle.get(j);
                Node n2 = area.get(j);

                if (n1.r != n2.r || n1.c != n2.c) {
                    isMatching = false;
                    break;
                }
            }

            // 퍼즐이 매칭이되면 즉시 참을 반환
            if (isMatching) {
                return true;
            }
            // 퍼즐을 90도 돌리기 r, c -> c, -r
            for (Node node : puzzle) {
                int temp = node.r;
                node.r = node.c;
                node.c = -temp;
            }
            // 회전이후 재정렬
            puzzle.sort(Comparator.naturalOrder());
        }
        return false;
    }

    public List<Node> bfs(int r, int c, boolean[][] visited, int[][] map, int target) {
        Queue<Node> que = new LinkedList<>();
        List<Node> nodes = new ArrayList<>();

        visited[r][c] = true;
        que.offer(new Node(r, c));
        nodes.add(new Node(0, 0));

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = rows[i] + curNode.r;
                int nextC = cols[i] + curNode.c;

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                if (!visited[nextR][nextC] && map[nextR][nextC] == target) {
                    visited[nextR][nextC] = true;
                    que.offer(new Node(nextR, nextC));
                    // (0, 0)기준으로 좌표 보정해서 퍼즐의 위치를 저장함
                    nodes.add(new Node(nextR - r, nextC - c));
                }
            }
        }
        // 좌표 기준 오름차순으로 정렬해야 동일한 위치를 비교할 수 있음
        nodes.sort(Comparator.naturalOrder());
        return nodes;
    }
}
