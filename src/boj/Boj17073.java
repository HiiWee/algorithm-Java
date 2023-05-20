package boj;

/*
    Map 자료형으로 트리를 구현한다.
    주어진 2개의 노드들 사이에 간선이 존재한다는 의미가 되므로
    양쪽 노드를 key로 가지고, value는 1씩 카운팅을 증가한다.
    주어진 간선에 대해 모든 카운팅을 마치면
    카운팅의 수가 1인 노드들은 리프노드가 되므로 리프노드들을 카운팅한다.

    이후 리프노드의 카운팅 값을 전체 물에서 나눠준다.
    (리프노드를 카운팅할때 루트노드도 카운팅이 1이 될 수 있으므로, 제외시켜주어야 한다.)
*/

import java.io.*;
import java.util.*;

class Boj17073 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeCount = Integer.parseInt(st.nextToken());
        int totalWater = Integer.parseInt(st.nextToken());

        List<Integer>[] trees = new ArrayList[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            trees[i] = new ArrayList<>();
        }

        int edgeCount = nodeCount - 1;
        while (edgeCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            trees[startNode].add(endNode);
            trees[endNode].add(startNode);
        }

        int leafNodeCount = 0;
        for (int currentNode = 2; currentNode <= nodeCount; currentNode++) {
            if (trees[currentNode].size() == 1) {
                leafNodeCount++;
            }
        }
        bw.write(String.format("%.15f", (totalWater / (double) leafNodeCount)));
        bw.flush();
        bw.close();
    }
}