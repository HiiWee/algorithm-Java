package boj;

/*
    원의 시작점과 끝점을 열린괄호, 닫힌괄호라고 생각하고, 스택을이용해 올바른 괄호쌍이 주어졌는지 확인하면 된다.


    - 2,020,001 크기의 sequences 배열을 생성하고 (원의 최소 시작점이 -1,010,000, 최대는 1,010,000이므로)
      중심점, 반지름을 -> 원의 시작점 ~ 끝점으로 변경한다 (중심 - 반지름) ~ (중심 + 반지름)
    - 이후 시작점, 끝점에 1,010,000을 더해 음수로 좌표가 생성되는것을 막는다.

    - 시작점, 끝점을 sequences 배열에 기록하는데, 서로 다른 좌표를 구분하기 위해 각 원들은 서로다른 수로 카운팅을 한다.

    - 초기 n + 1 크기의 visited 생성한다.
    - sequences 배열을 돌면서 1이상의 sequence값을 만났을때 visited[sequence]가 false라면 stack에 push및 true로 방문처리
    - 방문한 시퀀스값이라면 stack의 최상단값이 현재 시퀀스와 동일한지 파악하고(괄호체크) 같다면 pop 아니라면 NO를 출력한다.
      (예외 상황: 위 과정에서 stack이 버어있어도 NO가 출력되고, 모든 과정이 끝났을때 stack에 값이 남아있다면 NO를 출력한다.)
*/

import java.io.*;
import java.util.*;

public class Boj22942 {
    private static final int VALUE = 1010000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] sequences = new int[2020001];

        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int start = x - r;
            int end = x + r;
            sequences[start + VALUE] = i;
            sequences[end + VALUE] = i;
        }

        Stack<Integer> stack = new Stack<>();
        for (int sequence : sequences) {
            if (sequence > 0) {
                if (!visited[sequence]) {
                    stack.push(sequence);
                    visited[sequence] = true;
                } else if (!stack.isEmpty() && stack.peek() == sequence) {
                    stack.pop();
                } else {
                    bw.write("NO");
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
        if (stack.isEmpty()) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        bw.flush();
        bw.close();
    }
}
