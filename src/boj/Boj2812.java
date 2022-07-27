package boj;

/**
 * 1. 스택에 앞부터 차례대로 담는다.
 * 2. 담는 중에 스택의 마지막 값 < 현재 값이라면 pop을 한다.
 * 3. k개 만큼 제거했으면 종료
 *
 * 111111이나 54321같은경우 모두 수가 동일하거나 선행수가 후행수보다 항상 크므로 삭제되는값이 없으므로
 * 마지막에는 스택에서 n-k개 만큼만 값을 꺼내어 출력한다.
 */

import java.util.*;
import java.io.*;

class Boj2812 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String line = br.readLine();

        int[] arr = new int[n];
        for (int i = 0; i < line.length(); i++) {
            arr[i] = line.charAt(i) - '0';
        }
        int count = 0;
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && count < k && stk.peek() < arr[i]) {
                count++;
                stk.pop();
            }
            stk.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - k; i++) {
            sb.append(stk.get(i));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}