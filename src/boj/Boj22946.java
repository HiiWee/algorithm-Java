package boj;

/*
    1. 트리로 표현할 수 있어야 함
    2. 트리로 표현했을때 임의의 두 점에서 가장 먼 곳을 구하기 위해선 트리의 지름을 이용할 수 있다. (DFS 2번)

    1. 반지름이 큰 원은 작은원에 포함될 수 없다. 따라서 반지름의 내림차순으로 정렬하고
    반지름이 더 큰 A원과 다른 원들을 비교하여, A원 안에 다른 원이 하나라도 포함 되는 경우가 있다면 트리로 이어주고
    해당 원을 기준으로 다시 탐색을 들어갑니다.
    이때 좌표평면도 하나의 원으로 등록해놔야 모든 원을 탐색할 수 있습니다.

    2. 트리의 지름을 이용하여 DFS 두번으로 끝낼 수 있다.
*/
import java.util.*;
import java.io.*;

class Boj22946 {

    private static int n;
    private static List<Integer>[] tree;
    private static boolean[] visited;
    private static List<Circle> circles = new ArrayList<>();

    static class Circle {
        int number, x, y, r;

        Circle(int number, int x, int y, int r) {
            this.number = number;
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    private static int targetNode;
    private static int maxCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circles.add(new Circle(i, x, y, r));
        }
        // 반지름 내림차순 정렬
        circles.sort((c1, c2) -> c2.r - c1.r);
        circles.add(0, new Circle(0, 0, 0, 0));

        visited = new boolean[n + 1];
        makeTree(circles.get(0));

        visited = new boolean[n + 1];
        visited[0] = true;
        dfs(0, 0);

        visited = new boolean[n + 1];
        visited[targetNode] = true;
        dfs(targetNode, 0);

        bw.write(maxCount + "");
        bw.flush();
        bw.close();
    }

    public static void dfs(int node, int count) {
        // 단말 노드인 경우
        if (count > maxCount) {
            targetNode = node;
            maxCount = count;
        }

        for (int next : tree[node]) {
            if (!visited[next]) {
                visited[node] = true;
                dfs(next, count + 1);
            }
        }
    }

    public static void makeTree(Circle curCircle) {
        visited[curCircle.number] = true;

        for (Circle next : circles) {
            if (curCircle.number == 0 && !visited[next.number]) {
                tree[0].add(next.number);
                tree[next.number].add(0);
                makeTree(next);
            }
            if (!visited[next.number]) {
                long distance = getDistance(curCircle, next);
                if (distance < Math.abs(curCircle.r - next.r)) {
                    tree[curCircle.number].add(next.number);
                    tree[next.number].add(curCircle.number);
                    makeTree(next);
                }
            }
        }
    }

    public static int getDistance(Circle a, Circle b) {
        return (int) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}