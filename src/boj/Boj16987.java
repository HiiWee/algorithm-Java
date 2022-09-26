package boj;

/*
    먼저 계란 a, b, c가 있다고 가정하자
    규칙은 a를 들어 b를 친다. 여기서 a 혹은 b가 깨지거나 둘 다 깨지거나 깨지지 않는 경우가 발생한다.
    a로 다른 계란을 한번 쳤으므로 다음 계란을 집어 위의 과정을 반복한다.

    계란을 규칙에 따라서 순서대로 쳐가다가, 제일 오른쪽에 있는 계란까지 모두 한번씩 치거나 혹은 칠 수 없는 상황이 되면 계란을 초기화시킨다.
    즉, a 계란이 c를 치는 경우도 계산을 해야한다.(백트래킹)

    예외적인 상황
        1. 자기 자신이 깨져있다면 내려놓고 다음 오른쪽 + 1에 위치한 계란을 집어야 한다.
        2. 만약 자기자신빼고 다른 모든 계란이 깨져있다면 현재 집은 계란을 내려놓고 오른쪽 + 1칸의 계란을 집어야 한다.
*/

import java.io.*;
import java.util.*;

class Boj16987 {
    static int n;
    static int[][] eggs;

    static int maxCount;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        eggs = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        backtracking(0);

        bw.write(maxCount + "\n");
        bw.flush();
        bw.close();
    }

    public static void backtracking(int depth) {
        if (depth == n) {
            countEggs();
            return;
        }
        // 자기자신이 깨져있다면 내려놓아야 한다.
        if (isBreak(depth)) {
            backtracking(depth + 1);
            return;
        }
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (isBreak(i) || i == depth) {
                continue;
            }
            eggs[depth][0] -= eggs[i][1];
            eggs[i][0] -= eggs[depth][1];
            // 계란을 한번이라도 쳤는지 체크
            flag = true;
            backtracking(depth + 1);
            eggs[depth][0] += eggs[i][1];
            eggs[i][0] += eggs[depth][1];
        }
        // 한번도 자기자신빼고 모든 게란이 깨져있는경우는 계란을 내려놓고 다음 계란을 선택해야 한다.
        if (!flag) {
            backtracking(depth + 1);
        }
    }

    public static boolean isBreak(int index) {
        return eggs[index][0] <= 0;
    }


    public static void countEggs() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (eggs[i][0] <= 0) {
                count++;
            }
        }
        maxCount = Math.max(maxCount, count);
    }
}