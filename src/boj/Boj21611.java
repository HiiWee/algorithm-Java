package boj;

import java.io.*;
import java.util.*;

class Main {

    static class Node {
        int r, c, marble;

        public Node(int r, int c, int marble) {
            this.r = r;
            this.c = c;
            this.marble = marble;
        }
    }

    // 상하좌우
    static final int[] rows = {-1, 1, 0, 0};
    static final int[] cols = {0, 0, -1, 1};
    static final int[] spin = {2, 1, 3, 0};

    static List<Node> searchPath = new ArrayList<>();
    static int n, m;
    static int[][] map;
    static long[] popCount = new long[3];
    static Node shark;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        shark = new Node(n / 2, n / 2, 0);
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 맵을 탐색하는 순서대로 node를 미리 저장함
        savePath();

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            // 블리자드
            destroy(d, s);
            // 모든 구슬 담고 다시 순서대로 붙이기
            append(findMarbles());

            while (true) {
                // 4개 연속 구슬 터트리기
                if (!pop()) {
                    break;
                }
                append(findMarbles());
            }
            // 구슬 분열
            splitMarbles();
        }

        bw.write(Long.toString(popCount[0] + popCount[1] * 2 + popCount[2] * 3));
        bw.flush();
        bw.close();
    }

    public static void savePath() {
        int nextR = shark.r;
        int nextC = shark.c;
        int count = 0; // n칸으로 2회 갔다면 limit을 1칸 늘려줘야 함
        int limit = 1; // 현재방향으로 갈 수 있는 카운트
        int current = 0; // 현재 방향으로 얼마나 갔는지
        int dirIndex = 0; // 방향 인덱스 좌, 하, 우, 상
        for (int i = 0; i < n * n - 1; i++) {
            nextR += rows[spin[dirIndex]];
            nextC += cols[spin[dirIndex]];

            current++;
            searchPath.add(new Node(nextR, nextC, 0));
            if (limit == current) {
                if (count == 1) {
                    current = 0;
                    count = 0;
                    dirIndex = (dirIndex + 1) % 4;
                    limit++;
                } else {
                    count++;
                    current = 0;
                    dirIndex = (dirIndex + 1) % 4;
                }
            }
        }
    }

    public static void destroy(int direction, int distance) {
        int nextR = shark.r;
        int nextC = shark.c;
        for (int i = 0; i < distance; i++) {
            nextR += rows[direction];
            nextC += cols[direction];
            map[nextR][nextC] = 0;
        }
    }

    public static List<Integer> findMarbles() {
        List<Integer> numbers = new ArrayList<>();

        for (Node path : searchPath) {
            // 구슬만 담음
            if (map[path.r][path.c] > 0) {
                numbers.add(map[path.r][path.c]);
            }
        }
        return numbers;
    }

    public static void append(List<Integer> numbers) {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < numbers.size(); i++) {
            Node path = searchPath.get(i);
            newMap[path.r][path.c] = numbers.get(i);
        }
        map = newMap;
    }


    public static boolean pop() {
        boolean isPopAtLeastOnce = false;
        // 동일한 구슬들을 담을 큐
        Queue<Node> que = new LinkedList<>();
        for (Node path : searchPath) {
            if (map[path.r][path.c] == 0) {
                break;
            }

            // 큐가 비어있거나, 큐에 들어있는 구슬이 현재 구슬과 동일하면 한번에 터트려야 하므로 큐에 담음
            if (que.isEmpty() || que.peek().marble == map[path.r][path.c]) {
                que.offer(new Node(path.r, path.c, map[path.r][path.c]));
            } else if (que.size() >= 4) {
                isPopAtLeastOnce = true;
                // 동일하지 않고, 큐에 있는 구슬이 4개이상이라면 터트리며 카운팅함 이후 새로운 구슬을 담음
                popCount[que.peek().marble - 1] += que.size();
                while (!que.isEmpty()) {
                    Node node = que.poll();
                    map[node.r][node.c] = 0;
                }
                que.offer(new Node(path.r, path.c, map[path.r][path.c]));
            } else {
                // 4개 미만의 구슬이면 그대로 큐를 비우고 새로운 구슬을 담음
                que.clear();
                que.offer(new Node(path.r, path.c, map[path.r][path.c]));
            }
        }
        if (que.size() >= 4) {
            isPopAtLeastOnce = true;
            popCount[que.peek().marble - 1] += que.size();
            while (!que.isEmpty()) {
                Node node = que.poll();
                map[node.r][node.c] = 0;
            }
        }
        return isPopAtLeastOnce;
    }

    public static void splitMarbles() {
        int[][] newMap = new int[n][n];
        int marble = map[n / 2][n / 2 - 1];
        int count = 1;
        int index = 0;
        for (int i = 1; i < n * n - 1; i++) {
            // 인덱스가 범위를 벗어나면 남은 구슬 버림
            if (index == n * n - 1) {
                break;
            }
            Node path = searchPath.get(i);
            // 0이면 마지막으로 분열 한번하고 다음은 탐색할 필요없으니 종료
            if (map[path.r][path.c] == 0) {
                // 첫번째 숫자가 0이면 그대로 종료함
                if (i == 1) {
                    break;
                }
                Node countPath = searchPath.get(index);
                Node numberPath = searchPath.get(index + 1);
                // 숫자 -> 구슬번호로 입력해야함
                newMap[countPath.r][countPath.c] = count;
                newMap[numberPath.r][numberPath.c] = marble;
                break;
            }
            if (marble == map[path.r][path.c]) {
                count++;
            } else {
                Node countPath = searchPath.get(index);
                Node numberPath = searchPath.get(index + 1);
                // 숫자 -> 구슬번호로 입력해야함
                newMap[countPath.r][countPath.c] = count;
                newMap[numberPath.r][numberPath.c] = marble;
                index += 2;
                count = 1;
                marble = map[path.r][path.c];
            }

        }
        map = newMap;
    }
}