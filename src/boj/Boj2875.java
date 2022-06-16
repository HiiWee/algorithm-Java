package boj;

import java.util.*;
import java.io.*;

class Boj2875 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 여학생의 팀과, 남학생의 팀의 수를 구함
        // 여학생팀 >= 남학생팀: 여학생 팀에서 뺌
        // 여학생팀 < 남학생팀: 남학생 팀에서 뺌
        while (K > 0) {
            int girlTeam = N / 2;
            int boyTeam = M;

            if (girlTeam >= boyTeam) {
                N--;
                K--;
            } else {
                M--;
                K--;
            }
        }
        int teamCount = Math.min(N / 2, M);
        bw.write(teamCount + "\n");
        bw.flush();
        bw.close();

    }
}
