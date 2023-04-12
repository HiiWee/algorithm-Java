package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
/*
    1. 큐에 모두 저장
    2. 하나씩 뺴면서 큐 뒤로 다시 저장
    3. 이때 카운트하면서 K값이 도달하면 해당 값은 넣지 않고 출력
    큐가 빌때까지 반복
*/

class Boj1158_3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            que.offer(i);
        }

        List<Integer> results = new ArrayList<>();
        int count = 0;
        while (!que.isEmpty()) {
            int pollNumber = que.poll();
            count++;
            if (count == k) {
                results.add(pollNumber);
                count = 0;
                continue;
            }
            que.offer(pollNumber);
        }
        bw.write("<");
        for (int i = 0; i < results.size(); i++) {
            if (i == results.size() - 1) {
                bw.write("" + results.get(i));
                break;
            }
            bw.write(results.get(i) + ", ");
        }
        bw.write(">");
        bw.flush();
        bw.close();
    }
}