package programmers;

import java.util.*;

class 등산코스_정하기 {

    static class Node {
        int number, weight;

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }

    List<Node>[] graphs;
    Set<Integer> summitCheck = new HashSet<>();
    Set<Integer> gateCheck = new HashSet<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        // 동일한 거리라면 더 작은 번호를 반환해야 하므로
        Arrays.sort(summits);
        // 그래프 초기화
        initGraph(n, paths, gates, summits);
        // 탐색
        int[] intensity = dijkstra(n);

        answer[1] = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (answer[1] > intensity[summit]) {
                answer[1] = intensity[summit];
                answer[0] = summit;
            }
        }

        return answer;
    }

    public int[] dijkstra(int n) {
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        for (int gate : gateCheck) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            // curNode까지 갈 수 있는 최소 intensity보다 다른 경로로 curNode에 접근할때
            // 해당 경로의 weight가 기존 갱신값보다 크다면 굳이 탐색할 필요없음(가지치기)
            if (curNode.weight > intensity[curNode.number]) {
                continue;
            }
            for (Node nextNode : graphs[curNode.number]) {

                // 다음 노드의 기존 intensity가 현재노드 -> 다음노드로 갈때 누적하여 게산된 intensity보다 크다면 경로를 교체
                if (intensity[nextNode.number] > Math.max(intensity[curNode.number], nextNode.weight)) {
                    intensity[nextNode.number] = Math.max(intensity[curNode.number], nextNode.weight);
                    pq.offer(new Node(nextNode.number, intensity[nextNode.number]));
                }
            }
        }

        return intensity;
    }

    public void initGraph(int n, int[][] paths, int[] gates, int[] summits) {
        graphs = new ArrayList[n + 1];
        for (int summit : summits) {
            summitCheck.add(summit);
        }
        for (int gate : gates) {
            gateCheck.add(gate);
        }
        for (int i = 0; i <= n; i++) {
            graphs[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            if (gateCheck.contains(path[0]) || summitCheck.contains(path[1])) {
                graphs[path[0]].add(new Node(path[1], path[2]));
            } else if (gateCheck.contains(path[1]) || summitCheck.contains(path[0])) {
                graphs[path[1]].add(new Node(path[0], path[2]));
            } else {
                graphs[path[1]].add(new Node(path[0], path[2]));
                graphs[path[0]].add(new Node(path[1], path[2]));
            }
        }
    }
}