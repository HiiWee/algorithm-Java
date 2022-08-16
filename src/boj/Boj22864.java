package boj;

/*
    1. 먼저 피로도를 체크하면 두가지로 나뉨
        - 일을 할 수 있다.
        - 일을 할 수 없다.
    2. 일을 할 수 있다면 일량을 늘리고 피로도도 증가
    3. 일을 할 수 없다면 쉬고, 피로도를 줄임 (이 떄 0 밑으로 감소되지 않게 예외 처리)
    하나의 선택을할때마다 카운트를 하다가 24번을 완료하면 종료
    이후 최대 작업량 출력
*/

import java.io.*;
import java.util.*;

class Boj22864 {
    static final int DAY_HOUR = 24;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int A, B, C, M;    // 피로도, 작업량, 회복량, 피로도 한계
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int count = 0;
        int fatigue = 0;
        int workload = 0;
        while (count < DAY_HOUR) {
            // 일을 할 수 있음
            if (M >= fatigue + A) {
                fatigue += A;
                workload += B;
            } else {
                // 일을 할 수 없음
                fatigue = Math.max(0, fatigue - C);
            }
            count++;
        }

        bw.write(workload + "\n");
        bw.flush();
        bw.close();
    }
}