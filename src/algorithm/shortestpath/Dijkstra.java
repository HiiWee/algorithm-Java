package algorithm.shortestpath;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
sample input
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 */
public class Dijkstra {
    static int v, e, start;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        // 다음 노드의 번호, 그 노드로 가는데 드는 비용
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 방향 그래프 이므로 한쪽 방향으로만 삽입
            graph.get(s).add(new Node(e, c));
        }

        // 다익스트라 알고리즘 초기화
        int[] dist = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // 다익스트라 알고리즘은 항상 최소비용을 기준으로 경로를 구성해야 하므로 우선순위 큐를 이용하면 비교 횟수를 줄일 수 있다.
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        // 시작노드는 자기자신으로 가는 비용이 0임
        que.offer(new Node(start, 0));
        // 시작 노드를 골랐으니 해당 노드의 거리가 0으로 갱신됨
        dist[start] = 0;

        // 다익스트라 알고리즘 시작
        while (!que.isEmpty()) {
            Node curNode = que.poll();

            // 꺼낸 노드 = 현재 최소 비용을 갖는 노드.
            // 즉, 해당 노드의 비용이 현재 dist배열에 기록된 내용보다 크다면 고려할 필요가 없으므로 스킵한다.
            // 주의점 2 : 중복노드 방지1 : 만일, 이 코드를 생략한다면, 언급한 내용대로 이미 방문한 정점을 '중복하여 방문'하게 된다.
            // 만일 그렇다면, 큐에 있는 모든 다음 노드에대하여 인접노드에 대한 탐색을 다시 진행하게 된다.
            // 그래프 입력이 만일 완전 그래프의 형태로 주어진다면, 이 조건을 생략한 것 만으로 시간 복잡도가 E^2에 수렴할 가능성이 생긴다.
            if (dist[curNode.idx] < curNode.cost) {
                continue;
            }

            // 선택된 노드 주변 노드를 고려
            for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
                Node nextNode = graph.get(curNode.idx).get(i);

                // 만일, 주변 노드까지의 현재 dist값(비용)과 현재 선택된 노드로부터 주변 노드로 가는 비용을 비교하고, 더 작은 값을 선택한다.
                // 주의점 3 : 중복노드 방지 2 : 만일, 조건문 없이 조건문의 내용을 수행한다면 역시 중복 노드가 발생한다.
                // 간선에 연결된 노드를 모두 넣어준다면 앞서 언급한 정점의 시간 복잡도 VlogV를 보장할 수 없다.
                // 마찬가지로 E^2에 수렴할 가능성이 생긴다. 따라서 이 조건 안에서 로직을 진행해야만 한다.
                if (dist[nextNode.idx] > curNode.cost + nextNode.cost) {
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    que.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        System.out.println(Arrays.toString(dist));

    }

}
