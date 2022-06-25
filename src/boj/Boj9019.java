package boj;

/*
    D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다.
       그 결과 값(2n mod 10000)을 레지스터에 저장한다.
    S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다.
       n이 0 이라면 9999 가 대신 레지스터에 저장된다.
    L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다.
       이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
    R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다.
       이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.

    [주의] n의 자릿수로 0 이 포함된 경우에 주의해야 한다.
    예를 들어서 1000 에 L 을 적용하면 0001 이 되므로 결과는 1 이 된다.
    그러나 R 을 적용하면 0100 이 되므로 결과는 100 이 된다.
*/

import java.util.*;
import java.io.*;

class Boj9019 {
    static int start;
    static int end;
    static String[] dslr = {"D", "S", "L", "R"};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            bw.write(BFS(start) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static String BFS(int start) {
        String[] arr = new String[10000];
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        arr[start] = "";
        while (!que.isEmpty()) {
            int num = que.remove();
            for (int i = 0; i < 4; i++) {
                int nextNum = calcDSLR(i, num);

                if (arr[nextNum] == null) {
                    que.add(nextNum);
                    arr[nextNum] = arr[num] + dslr[i];
                }
                if (nextNum == end) {
                    return arr[nextNum];
                }
            }
        }
        return "";
    }

    public static int calcDSLR(int index, int num) {
        if (index == 0) {
            num *= 2;
            return num % 10000;
        } else if (index == 1) {
            if (num == 0) {
                return 9999;
            } else {
                return num - 1;
            }
        } else if (index == 2) {
            int n = num / 1000;
            num %= 1000;
            num *= 10;
            num += n;
            return num;
        } else {
            int n = num % 10;
            num /= 10;
            num += n * 1000;
            return num;
        }
    }
}
