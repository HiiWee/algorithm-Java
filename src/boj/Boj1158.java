package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// List 및 나머지 연산으로 구현
class Boj1158 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int index = 0;
        System.out.print("<");
        while (!list.isEmpty()) {
            index = (index + k - 1) % list.size();
            if (list.size() > 1) {
                System.out.print(list.get(index) + ", ");
            } else {
                System.out.print(list.get(index));
            }
            list.remove(index);
        }
        System.out.println(">");

    }
}
// Queue이용
class Boj1158_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            que.add(i);
        }

        int index = 1;
        System.out.print("<");
        while (que.size() > 1) {
            if (index % k == 0) {
                System.out.print(que.poll() + ", ");
            } else {
                que.add(que.poll());
            }
            index++;
        }
        System.out.print(que.poll());
        System.out.println(">");

    }
}