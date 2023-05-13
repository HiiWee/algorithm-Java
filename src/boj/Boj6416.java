package boj;
/*
    트리가 되는 조건
    1. 노드가 0인 경우도 트리다.
    2. 트리의 부모는 1개여아 한다.
    3. 트리는 반드시 1개의 루트 노드를 가진다.

    노드가 0인 경우는 Map 자료형에 저장된 값이 하나도 없다면으로 검증할 수 있다.

    Map자료형의 key값으로 노드의 번호를 value 값으로 노드의 부모 노드의 개수를 저장한다.
    이후 순회를 하면서 value가 0이라면 루트노드이므로 루트노드 카운팅을한다.
    이때 부모 노드의 개수가 2개 이상이라면 트리가 아니므로 이를 체크해주어야 한다.
    또한 간선의 수는 N-1개 여야한다.

    순회를 마치고 root노드의 개수가 1개이고, 다른 모든 노드의 부모노드수가 1개라면 트리로 간주하고
    하나라도 지켜지지 않는다면 트리가 아니다.
*/

import java.io.*;
import java.util.*;

class Boj6416 {
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int currentCase = 1;
        while (true) {
            Map<Integer, Integer> trees = new HashMap<>();
            int edgeCount = 0;

            while (true) {
                int parentNode = sc.nextInt();
                int childNode = sc.nextInt();

                if (parentNode == -1 && childNode == -1) {
                    bw.write(result.toString());
                    bw.flush();
                    bw.close();
                    return;
                }
                if (parentNode == 0 && childNode == 0) {
                    break;
                }

                edgeCount++;
                trees.put(parentNode, trees.getOrDefault(parentNode, 0));
                trees.put(childNode, trees.getOrDefault(childNode, 0) + 1);
            }

            int rootCount = 0;
            boolean hasTwoParents = false;
            for (int keyNode : trees.keySet()) {
                if (trees.get(keyNode) == 0) {
                    rootCount++;
                } else if (trees.get(keyNode) > 1) {
                    hasTwoParents = true;
                    break;
                }
            }
            if (trees.isEmpty()) {
                result.append(String.format("Case %d is a tree.\n", currentCase));
            } else if (rootCount == 1 && !hasTwoParents && trees.size() - 1 == edgeCount) {
                result.append(String.format("Case %d is a tree.\n", currentCase));
            } else {
                result.append(String.format("Case %d is not a tree.\n", currentCase));
            }

            currentCase++;
        }
    }
}