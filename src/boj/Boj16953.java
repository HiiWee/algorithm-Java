package boj;

/*
    결과 수 -> 초기 수로 가는 방법을 생각한다.
    일단 2로 나누어 떨어지면 게속 나누고, 홀수라면 1을 빼주면서 연산을 더해간다.
    만약에 초기 수와 동일하면 해당 카운트를 출력하지만 동일하지 않고, 작아진다면 -1 출력

    또한 홀수인경우는 1의 자리가 1인 경우밖에 존재하지 않음 따라서 1이 아닌 홀수가 1의 자리에 존재하면 바로 종료
*/

import java.util.*;
import java.io.*;

class Boj16953 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int count = 0;

        while (end != start) {
            if (end % 2 == 0) {
                end /= 2;
            } else if (end % 10 == 1) {
                end -= 1;
                end /= 10;
            } else {
                count = -1;
                break;
            }
            count++;
            if (end < start) {
                count = -1;
                break;
            }
        }
        if (count == -1) {
            bw.write("-1");
        } else {
            count++;
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }
}