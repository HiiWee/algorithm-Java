package boj;

/*
    거리를 직접적으로 계산하는것이 아닌 사람을 기준으로 생각
    가장 왼쪽 지점부터 시작할때 한칸 오른칸으로 이동하게 될 경우
        1. 이동한 지점에서 왼쪽에 위치한 사람의 수만큼 거리의 손해 발생
        2. 이동하기 전 지점을 기준으로 오른쪽에 있던 사람의 수만큼의 거리가 이득으로 변환
    따라서 우체국을 지을 위치는 우체국 기준으로 왼쪽 마음의 사람들과 오른쪽 사람들의 수가 동일하거나
    그 차가 최소가 되어야 한다.

    마을을 오름차순으로 방문하며 사람을 더하다가 아래 식을 만족하면 출력하고 종료하면 된다.
    누적사람수 >= (총 사람 + 1) / 2
    +1을 더하고 2를 나누는 이유는 다음과 같다.
    15명의 경우 2로 나누면 7.5가 되고 정석적인 답이라면 누적 사람수가 7명이면 오답, 8명이면 정답이 된다.
    따라서 이런 소수의 처리를 간단히 하기위해 1을 더해준다.

    그렇다면 짝수에서 문제가 생가지 않나? 아니다
    만약 총 사람수가 6명일때 위의 +1을 하지 않아도 6 / 2 == 3이고
    +1을 하더라도 7 / 2 == 3이므로(소수점 버려짐) 상관이 없다.
*/

import java.util.*;
import java.io.*;

class Boj2141 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n][2];

        long total = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Long.parseLong(st.nextToken());
            arr[i][1] = Long.parseLong(st.nextToken());
            total += arr[i][1];
        }

        Arrays.sort(arr, (o1, o2) -> {
            return (int) (o1[0] - o2[0]);
        });

        long count = 0;
        long location = 0;
        for (int i = 0; i < n; i++) {
            count += arr[i][1];
            if (count >= (total + 1) / 2) {
                location = arr[i][0];
                break;
            }
        }
        bw.write(location + "\n");
        bw.flush();
        bw.close();
    }
}