package boj;

/*
    그룹에는 일반 블록이 적어도 하나 있어야 하며,
    일반 블록의 색은 모두 같아야 한다.
    검은색 블록은 포함되면 안 되고,
    무지개 블록은 얼마나 들어있든 상관없다.

    그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며,
    임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다.

    그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록,
    그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.
*/
import java.io.*;
import java.util.*;

class Boj21609 {

    static final int INF = -2;
    static final int[] rows = {-1, 0, 1, 0};
    static final int[] cols = {0, 1, 0, -1};

    static class Group {
        int r, c, rainbowSize;
        List<Node> nodes;

        public Group(final List<Node> nodes, final int rainbowSize) {
            this.nodes = nodes;
            this.rainbowSize = rainbowSize;
        }
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        while (true) {
            // 그룹 구하기
            Group groups = findGroups();
            if (groups.nodes.size() < 2) {
                break;
            }
            score += (int) Math.pow(groups.nodes.size(), 2);
            // 그룹 제거
            deleteGroups(groups);
            // 중력 작용
            pullDown();
            // 90도 반시계 회전
            rotateCounterClockwise();
            // 중력 작용
            pullDown();
        }

        bw.write(score + "");
        bw.flush();
        bw.close();
    }

    public static void rotateCounterClockwise() {
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[j][n - 1 - i];
            }
        }
        map = copyMap;
    }

    public static void pullDown() {
        for (int i = 0; i < n; i++) {
            int startIndex = n - 1;
            for (int next = n - 1; next >= 0; next--) {
                if (map[next][i] == -1) {
                    pullDownBetween(startIndex, next, i);
                    startIndex = next - 1;
                }
            }
            pullDownBetween(startIndex, -1, i);
        }
    }

    public static void pullDownBetween(int startInclusive, int endExclusive, int col) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = startInclusive; i > endExclusive; i--) {
            if (map[i][col] != INF) {
                // 빈칸이 아닌 숫자 전부 복사
                numbers.add(map[i][col]);
                map[i][col] = INF;
            }
        }
        // 하나씩 차례로 붙여넣음
        int index = startInclusive;
        for (int number : numbers) {
            map[index--][col] = number;
        }
    }

    public static void deleteGroups(Group group) {
        for (Node n : group.nodes) {
            map[n.r][n.c] = INF;
        }
    }

    public static Group findGroups() {
        boolean[][] visited = new boolean[n][n];
        boolean[][][] rainbowVisited = new boolean[n][n][m + 1];

        Group compareGroups = new Group(Collections.emptyList(), 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] >= 1) {
                    Group groups = bfs(rainbowVisited, visited, new Node(i, j));
                    if (groups.nodes.isEmpty()) {
                        continue;
                    }
                    // 사이즈가 더 크다면 교체
                    if (compareGroups.nodes.size() < groups.nodes.size()) {
                        compareGroups = groups;
                        // 사이즈가 동일하다면
                    } else if (compareGroups.nodes.size() == groups.nodes.size()) {
                        // 무지개 블록 크기 비교
                        if (compareGroups.rainbowSize < groups.rainbowSize) {
                            compareGroups = groups;
                            // 무지개 블록 크기마저 동일하다면
                        } else if (compareGroups.rainbowSize == groups.rainbowSize) {
                            // 기준 노드 중에서 더 큰 행을 가진 그룹, 행이 동일하다면 더 큰 열을 가진 그룹을 선택
                            if (compareGroups.r < groups.r) {
                                compareGroups = groups;
                            } else if (compareGroups.c < groups.c) {
                                compareGroups = groups;
                            }
                        }
                    }
                }
            }
        }
        return compareGroups;
    }

    public static Group bfs(final boolean[][][] rainbowVisited, boolean[][] visited, Node startNode) {
        List<Node> totalNodes = new ArrayList<>();
        Group group = new Group(totalNodes, 0);
        group.r = startNode.r;
        group.c = startNode.c;

        totalNodes.add(startNode);

        int target = map[startNode.r][startNode.c];
        visited[startNode.r][startNode.c] = true;

        Queue<Node> que = new LinkedList<>();
        que.offer(startNode);
        int rainbowCount = 0;
        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + rows[i];
                int nextC = curNode.c + cols[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                // 지워진 블록이거나, 검은블록이거나, 이미 방문한 블록이면 패스
                if (map[nextR][nextC] == INF || map[nextR][nextC] == -1 || visited[nextR][nextC]) {
                    continue;
                }
                if (map[nextR][nextC] == 0 && !rainbowVisited[nextR][nextC][target]) {
                    rainbowVisited[nextR][nextC][target] = true;
                    Node nextNode = new Node(nextR, nextC);
                    que.offer(nextNode);
                    totalNodes.add(nextNode);
                    rainbowCount++;
                } else if (map[nextR][nextC] == target) {
                    Node nextNode = new Node(nextR, nextC);
                    que.offer(nextNode);
                    visited[nextR][nextC] = true;
                    totalNodes.add(nextNode);
                }
            }
        }
        // 찾은 그룹 반환
        group.rainbowSize = rainbowCount;
        return group;
    }
}