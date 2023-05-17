package boj;
/*
    트리를 연결리스트 구조로 저장을하고,
    주어지는 간선을 순서에 맞춰서 별도로 저장한다.

    단절점을 구분하는 방법(숫자 1)
    지우고자 하는 정점이 2개 이상의 간선과 연결되어 있다면 지웠을때 2개의 그래프 생성이 보장된다.
    따라서 yes
    반대로 정점이 2개 미만의 간선과 연결되어있거나 간선이 없는 경우 no

    단절선을 구분하는 방법(숫자2)
    우선 간선이 있다는것 자체의 의미를 생각해보면 2개이상의 정점이 연결되어 있다는 의미이므로
    어떤 간선을 지우든 yes다.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Boj14675 {
    private static int[][] edges;
    private static Map<Integer, List<Integer>> trees = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        edges = new int[n][2];

        for (int i = 1; i <= n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startVertex = Integer.parseInt(st.nextToken());
            int endVertex = Integer.parseInt(st.nextToken());
            edges[i][0] = startVertex;
            edges[i][1] = endVertex;
            putVertexToTree(startVertex, endVertex);
        }

        StringBuilder result = new StringBuilder();
        int questionCount = Integer.parseInt(br.readLine());
        while (questionCount-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            if (command == 1) {
                if (!trees.containsKey(number) || trees.get(number).size() < 2) {
                    result.append("no\n");
                } else {
                    result.append("yes\n");
                }
            } else {
                result.append("yes\n");
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void putVertexToTree(int startVertex, int endVertex) {
        if (trees.containsKey(startVertex)) {
            trees.get(startVertex).add(endVertex);
        } else {
            List<Integer> vertexes = new ArrayList<>();
            vertexes.add(endVertex);
            trees.put(startVertex, vertexes);
        }
        if (trees.containsKey(endVertex)) {
            trees.get(endVertex).add(startVertex);
        } else {
            List<Integer> vertexes = new ArrayList<>();
            vertexes.add(startVertex);
            trees.put(endVertex, vertexes);
        }
    }
}