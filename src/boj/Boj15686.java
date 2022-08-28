package boj;

/*
    모든 치킨칩 S에 대해 M개의 조합을 모두 구한다. sCm 이후 각 조합에서 각 집과 해당 조합안에 치킨집들의
    최소거리를 각각 구하고 최종적으로 더한 값에서 최소값을 구하면 된다.
*/
import java.util.*;
import java.io.*;

class Boj15686_1 {
    static int n, m, min = Integer.MAX_VALUE;
    static List<Node> chicken = new ArrayList<>();
    static List<Node> home = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();


    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    home.add(new Node(i, j));
                } else if (num == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }
        combination(new boolean[chicken.size()], chicken.size(), m, 0);

        // 각 조합을 꺼내고
        // 사람들을 한명 씩 뽑아서 각 조합의 치킨집들과의 거리를 구하고 최소 거리를 선택하고 각각의 사람들의 최소 치킨집 거리의 가중치를 더함
        // 이후 모든 사람에 대해서 종료되면 가중치의 최소값을 갱신하고 다음 조합을 탐색

        String[] combinations = sb.toString().split(",");

        for (String eachCombi : combinations) {
            int sum = 0;
            for (Node eachHome : home) {
                int minDistance = Integer.MAX_VALUE;
                for (String eachChicken : eachCombi.split(" ")) {
                    Node chick = chicken.get(Integer.parseInt(eachChicken));
                    minDistance = Math.min(minDistance, getDistance(eachHome, chick));
                }
                sum += minDistance;
            }
            min = Math.min(sum, min);
        }

        bw.write(min + "\n");
        bw.flush();
        bw.close();


    }

    public static int getDistance(Node node1, Node node2) {
        return Math.abs(node1.r - node2.r) + Math.abs(node1.c - node2.c);
    }

    public static void combination(boolean[] visited, int n, int r, int depth) {
        if (r == 0) {
            append(visited);
            return;
        }

        for (int i = depth; i < n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, i + 1);
            visited[i] = false;
        }
    }

    public static void append(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                sb.append(i).append(" ");
            }
        }
        sb.append(",");
    }
}

/*
    모든 치킨집을 선택할때 M개 이하 개수로 선택하는 조합을 모두 구해야 한다.
    최대 M개의 치킨집을 고를 수 있으므로 굳이 M개가 아니어도 되기 때문이다.

    집과 치킨집을 담는 리스트를 만들고 해당 좌표값을 리스트에 담고, M개이하의 조합을 구하여
    각 모든 사람들에 대해 해당 조합에서 최소 거리의 치킨집을 구하여 모든 사람들의 최소 거리의 가중치를 구한다.
    이후 구한 가중치를 업데이트하며 최소 가중치를 구한다.
*/

class Boj15686_2 {
    static int n, m, min = Integer.MAX_VALUE;
    static List<Node> chicken = new ArrayList<>();
    static List<Node> home = new ArrayList<>();


    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    home.add(new Node(i, j));
                } else if (num == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }
        backTracking(new boolean[chicken.size()], 0);

        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    public static void backTracking(boolean[] visited, int index) {
        if (index == chicken.size()) {
            if (check(visited)) {
                min = Math.min(min, calcMinDist(visited));
            }
            return;
        }

        visited[index] = true;
        backTracking(visited, index + 1);
        visited[index] = false;
        backTracking(visited, index + 1);
    }

    public static boolean check(boolean[] visited) {
        int count = 0;
        for (int i = 0; i < chicken.size(); i++) {
            if (visited[i]) {
                count++;
            }
        }
        // 0개의 치킨집을 선택하거나, m개 초과의 치킨집이 선택되면 안됨
        if (count == 0 || count > m) {
            return false;
        }
        return true;
    }

    public static int calcMinDist(boolean[] visited) {
        int sum = 0;
        for (Node eachHome : home) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < chicken.size(); i++) {
                if (visited[i]) {
                    Node eachChicken = chicken.get(i);
                    minDist = Math.min(minDist, getDistance(eachHome, eachChicken));
                }
            }
            sum += minDist;
        }
        return sum;
    }

    public static int getDistance(Node node1, Node node2) {
        return Math.abs(node1.r - node2.r) + Math.abs(node1.c - node2.c);
    }
}