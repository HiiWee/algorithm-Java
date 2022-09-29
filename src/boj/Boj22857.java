package boj;

/*
    투 포인터를 이용해 풀어보자
    l, r은 같은 점에서 시작해 r은 포인터를 증가시키며 홀수를 만나면 카운트 값을 증가시킨다,
    만약 카운트의 값과 k의 수가 동일해지면 k개의 홀수를 제거했을때 l과 r 사이에 있는 짝수의 값이 길이가 되므로
    최대값을 갱신한다.

    이후 만약 count가 k와 동일하다면 l을 증가시키며 l인덱스의 값이 홀수라면 카운트를 뺴준다. 이후 다시 r을 증가시키며
    전체 수열에서 부분수열의 최대값을 탐색한다.
*/

import java.io.*;
import java.util.*;

class Boj22857 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 0;
        int count = 0;
        int answer = 0;

        while (r < n) {
            if (count <= k) {
                count += arr[r++] % 2;
            } else {
                count -= arr[l++] % 2;
            }
            if (count <= k) {
                answer = Math.max(answer, r - l - count);
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}