package boj;

import java.io.*;
import java.util.*;

/*
    처음부터 모든 숫자를 돌면서 (최대 숫자 999999를 누르는 경우까지 존재)
    부서진 버튼이 포함안된 숫자라면 횟수를 카운트하고 최소값을 갱신한다.
    + 버튼이 아무것도 망가지지 않을 수 있으므로 예외처리 해줘야함
    + 초기 min값은 100에서 +혹은 -버튼으로만 이동할 수 있는 거리를 둬야 함
*/

class Boj1107 {
    static int[] button;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String N = br.readLine();
        int M = Integer.parseInt(br.readLine());
        button = new int[10];

        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                button[Integer.parseInt(st.nextToken())] = -1;
            }
        }

        int min = Math.abs(100 - Integer.parseInt(N));
        for (int i = 0; i <= 999999; i++) {
            String num = String.valueOf(i);
            if (isNotBroken(num)) {
                min = Math.min(min, num.length() + Math.abs(i - Integer.parseInt(N)));
            }
        }
        bw.write(min + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean isNotBroken(String n) {
        for (int i = 0; i < n.length(); i++) {
            int eachNum = n.charAt(i) - '0';
            if (button[eachNum] == -1) {
                return false;
            }
        }
        return true;
    }
}