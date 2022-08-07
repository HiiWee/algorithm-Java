package boj;

/*
    순서는 A~Za~z까지 이므로 해당 아스키 코드값에 맞는 배열을 생성한다.
    다만 A == 65이므로 0부터 인덱스를 시작하기 위해서는 Char-65를 하여 배열에 저장함
    중간에 알파벳이 아닌 값이나 map[i][i]같이 전건과 후건이 같은 경우는 INF 처리 해준다.
    경로의 가중치를 구하는 것이 아닌 갈 수 있는지만 확인하므로 boolean타입의 배열을 이용해도 좋다.
*/

import java.util.*;
import java.io.*;

class Boj2224 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] statement = new boolean[58][58];
        for (int i = 0; i < 58; i++) {
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " => ");
            int before = st.nextToken().charAt(0) - 'A';
            int after = st.nextToken().charAt(0) - 'A';
            statement[before][after] = true;
        }

        for (int middle = 0; middle < 58; middle++) {
            for (int from = 0; from < 58; from++) {
                for (int to = 0; to < 58; to++) {
                    if (statement[from][middle] && statement[middle][to]) {
                        statement[from][to] = true;
                    }
                }
            }
        }
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int before = 0; before < 58; before++) {
            for (int after = 0; after < 58; after++) {
                if (before != after && statement[before][after]) {
                    count++;
                    sb.append((char)(before + 'A')).append(" => ").append((char)(after + 'A')).append("\n");
                }
            }
        }
        bw.write(count + "\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
}