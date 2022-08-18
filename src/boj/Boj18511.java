package boj;

/*
    현재 채워야 하는 인덱스와 만든 num값을 넘기면서 재귀함수를 돌린다.
    만약 index가 n의 자릿수와 동일해지면 n보다 작은 값 중에 최대값인지 판별하여 갱신한다.
    단
    4111 3
    4 8 9
    와 같은 반례는 자릿수 하나를 제거해줘야 최대값을 구할 수 있으므로
    자릿수를 하나씩 낮추면서 최대값을 구해주며 갱신해야 최대값을 구할 수 있다.
*/

import java.util.*;
import java.io.*;


class Boj18511_1 {
    static int max = Integer.MIN_VALUE;
    static int k;
    static int[] arr;
    static String n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = st.nextToken();
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n.length(); i++) {
            recursive(i, "");
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void recursive(int index, String num) {
        if (index == n.length()) {
            int numN = Integer.parseInt(n);
            int compareNum = Integer.parseInt(num);
            if (numN >= compareNum && max < compareNum) {
                max = compareNum;
            }
            return;
        }

        for (int i : arr) {
            recursive(index + 1, num + i);
        }
    }
}

/*
    위에서 푼 방식보다 좀 더 직관적이다.
    단순히 현재 숫자 * 10 + 다음숫자를 재귀적으로 반복해 일단 n보다 큰수가 나온다면 계속 갱신한다.
    단 n보다 크다면 return을 통해 종료한다.
 */
class Boj18511_2 {
    static int max = Integer.MIN_VALUE;
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        recursive(0);

        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    public static void recursive(int num) {
        if (num > n) {
            return;
        }
        max = Math.max(num, max);

        for (int i : arr) {
            recursive(num * 10 + i);
        }
    }
}