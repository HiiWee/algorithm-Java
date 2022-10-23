package boj;

/*
    - 큐에 들어가는 각 원소들은 우선순위와 인덱스를 가져야 하므로 이를 클래스로 감싼다.
        - 각 테스트 케이스마다 인덱스와, 우선순위를 기록해놓는다.
    - 우선순위의 빈도를 위해 나타나는 우선순위들을 배열에 기록을 해놓는다.

    큐에서 데이터를 뽑았을때 해당 우선순위보다 높은 우선순위의 데이터가 존재하면 큐에 재 삽입
    그렇지 않다면 뽑으면서 순서를 증가한다. 이때 찾고자 하는 인덱스라면 종료 후 순서 출력
*/

import java.io.*;
import java.util.*;

class Boj1966 {
    static class Node {
        int index, priority;
        Node(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            Queue<Node> que = new LinkedList<>();
            int[] priorities = new int[10];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int priority = Integer.parseInt(st.nextToken());
                priorities[priority]++;
                que.add(new Node(i, priority));
            }

            int index = 1;
            while (true) {
                Node node = que.poll();
                if (hasBiggerPriority(node.priority, priorities)) {
                    que.offer(node);
                } else {
                    if (node.index == m) {
                        sb.append(index).append("\n");
                        break;
                    }
                    priorities[node.priority]--;
                    index++;
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean hasBiggerPriority(int priority, int[] priorities) {
        for (int i = priority + 1; i <= 9; i++) {
            if (priorities[i] > 0) {
                return true;
            }
        }
        return false;
    }
}