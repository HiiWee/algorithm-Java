package boj;

/*
    우선 BFS를 시작할때 시작점과 상어의 레벨을 지정한다.
    아기상어는 전체를 방문체크 및 탐색하며 자신이 먹을 수 있는 먹이를 발견하면 map을 리스트에 저장한다.
    모든곳의 방문이 종료되면 리스트를 row의 오름차순으로 정렬하고 동일한 row에 대해서 col의 오름차순으로 정렬한다.
    이후 리스트의 첫번쨰 요소를 방문하고, 해당 좌표까지 걸리는 시간을 반환한다.

    만약 아무런 곳도 방문하지 못했다면 -1을 반환하고, main에선 -1을 받게되면 직전에 탐색했던 초를 출력한다.
*/

import java.io.*;
import java.util.*;

// 일반 큐 이용
class Boj16236_1 {
    static int n, fishCount, fishLevel = 2, totalTime;
    static int[][] map;
    static boolean[][] visited;
    static Node startNode;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    static class Node {
        int r, c, time;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public void setTime(int time) {
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    startNode = new Node(i, j);
                } else if (num != 0) {
                    fishCount++;
                }
                map[i][j] = num;
            }
        }

        int count = 0;
        while (fishCount-- > 0) {
            visited = new boolean[n][n];
            int time = BFS(startNode, fishLevel);
            if (time == -1) {
                break;
            }
            count++;
            totalTime += time;
            if (count == fishLevel) {
                fishLevel++;
                count = 0;
            }
        }
        bw.write(totalTime + "\n");
        bw.flush();
        bw.close();
    }
    public static int BFS(Node start, int level) {
        List<Node> list = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        int[][] timeMap = new int[n][n];
        que.add(start);
        visited[start.r][start.c] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + row[i];
                int nextC = curNode.c + col[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                // 방문하지 않고, 물고기 크기가 아기상어보다 작거나 같을경우만
                if (!visited[nextR][nextC] && map[nextR][nextC] <= level) {
                    Node next = new Node(nextR, nextC);
                    timeMap[nextR][nextC] = timeMap[curNode.r][curNode.c] + 1;

                    // 0이 아니고 아기상어보다 크기가 작다면 먹을 수 있으므로 리스트에 추가하고
                    if (map[nextR][nextC] < level && map[nextR][nextC] != 0) {
                        next.setTime(timeMap[nextR][nextC]);
                        list.add(next);
                    }
                    que.add(next);
                    visited[nextR][nextC] = true;
                }
            }
        }
        if (list.isEmpty()) {
            return -1;
        } else {
            Node select = selectNode(list);
            // 결정된 물고기는 아기상어의 마지막 위치가 되므로 아기상어의 위치도 변경해준다.
            map[start.r][start.c] = 0;
            startNode = select;
            map[select.r][select.c] = 9;
            return timeMap[select.r][select.c];
        }
    }

    public static Node selectNode(List<Node> list) {
        list.sort((o1, o2) -> {
            if (o1.time == o2.time) {
                if (o1.r == o2.r) {
                    return o1.c - o2.c;
                } else {
                    return o1.r - o2.r;
                }
            } else {
                return o1.time - o2.time;
            }
        });
        return list.get(0);
    }
}

// 우선순위 큐 이용
class Boj16236_2 {
    static int n, fishCount, fishLevel = 2, totalTime;
    static int[][] map;
    static boolean[][] visited;
    static Node startNode;
    static int[] row = {-1, 0, 1, 0};
    static int[] col = {0, 1, 0, -1};

    static class Node implements Comparable<Node> {
        int r, c, time;
        public Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        // 시간 -> 행 -> 열 순으로 오름차순 정렬
        public int compareTo(Node o) {
            if (this.time != o.time) {
                return this.time - o.time;
            }
            if (this.r != o.r) {
                return this.r - o.r;
            }
            return this.c - o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 9) {
                    startNode = new Node(i, j, 0);
                } else if (num != 0) {
                    fishCount++;
                }
                map[i][j] = num;
            }
        }


        int count = 0;
        while (fishCount-- > 0) {
            visited = new boolean[n][n];
            // BFS를 돌며 저장하는 startNode의 time값이 0이 아닐수도 있기때문에 초기화
            startNode.time = 0;
            int time = BFS(startNode, fishLevel);
            if (time == -1) {
                break;
            }
            count++;
            totalTime += time;
            // 상어 성장
            if (count == fishLevel) {
                fishLevel++;
                count = 0;
            }
        }
        bw.write(totalTime + "\n");
        bw.flush();
        bw.close();
    }

    public static int BFS(Node start, int level) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Queue<Node> que = new LinkedList<>();
        que.add(start);
        visited[start.r][start.c] = true;

        while (!que.isEmpty()) {
            Node curNode = que.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = curNode.r + row[i];
                int nextC = curNode.c + col[i];

                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) {
                    continue;
                }
                // 방문하지 않고, 물고기 크기가 아기상어보다 작거나 같을경우만
                if (!visited[nextR][nextC] && map[nextR][nextC] <= level) {
                    Node next = new Node(nextR, nextC, curNode.time + 1);

                    // 0이 아니고 아기상어보다 크기가 작다면 먹을 수 있으므로 pq에 추가하면 우선순위큐가 자동적으로 정렬해서 보관
                    if (map[nextR][nextC] < level && map[nextR][nextC] != 0) {
                        pq.add(next);
                    }
                    que.add(next);
                    visited[nextR][nextC] = true;
                }
            }
        }
        if (pq.isEmpty()) {
            return -1;
        } else {
            Node select = pq.poll();
            // 결정된 물고기는 다음 탐색에서 아기상어의 출발위치가 되므로 아기상어의 위치도 변경해준다.
            map[start.r][start.c] = 0;
            map[select.r][select.c] = 9;
            // 시작 노드를 변경해준다.
            startNode = select;
            return select.time;
        }
    }
}