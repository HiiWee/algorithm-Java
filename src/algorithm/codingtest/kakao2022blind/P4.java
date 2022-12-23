package algorithm.codingtest.kakao2022blind;

/*
    먼저 각 numbers를 이진수로 변환하고 해당 이진수를 포함할 수 있는 포화이진트리의 높이를 결정해야한다.
    따라서 2^(n+1) - 1의 공식을 통해 이진수의 길이보다 길어지는 n값을 정한다.
*/
class Solution4 {
    int nodeCount;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            nodeCount = 0;
            String binary = Long.toBinaryString(numbers[i]);
            // 1의 개수 구하기
            int numOfOne = getCountOfOne(binary);
            // 포화 이진트리 구성 시 필요한 전체 노드 수 구하기
            int totalNode = getNodeCount(binary.length());
            // 포화이진트리시 추가할 더미 노드 수 구하기
            int zeroCount = totalNode - binary.length();

            // 더미노드를 추가한 전체 포화 이진트리 구성하기
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < zeroCount; j++) {
                sb.append(0);
            }
            sb.append(binary);

            // 시작 루트부터 끝까지 연결된 트리를 파고들며 1의 개수 카운팅하기
            countTreeNode(sb.toString());

            // 1의 개수가 다르다면 트리가 끊어져 있는 것이므로 0 입력 그렇지 않다면 1 입력
            if (nodeCount != numOfOne) {
                answer[i] = 0;
            } else {
                answer[i] = 1;
            }
        }



        return answer;
    }

    public void countTreeNode(String binary) {
        // 리프 노드 도착시 자기자신만 판별하고 카운팅 후 종료
        if (binary.length() == 1) {
            if (binary.charAt(0) == '1') {
                nodeCount++;
            }
            return;
        }
        int rootIdx = binary.length() / 2;
        int leftChild = (rootIdx - 1) / 2;
        int rightChild = ((rootIdx + 1) + (binary.length() - 1)) / 2;

        if (binary.charAt(rootIdx) == '0') {
            return;
        } else {
            nodeCount++;
        }
        if (binary.charAt(leftChild) == '1') {
            countTreeNode(binary.substring(0, rootIdx));
        }
        if (binary.charAt(rightChild) == '1') {
            countTreeNode(binary.substring(rootIdx + 1, binary.length()));
        }

    }

    // 포화이진트리로 구성될때의 전체 노드 개수 카운트
    public int getNodeCount(int length) {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            count = (int) Math.pow(2, i + 1) - 1;
            if (count >= length) {
                break;
            }
        }
        return count;
    }

    // 1의 개수 카운트
    public int getCountOfOne(String binary) {
        int count = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}
