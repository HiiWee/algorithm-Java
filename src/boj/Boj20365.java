package boj;

/*
    영역의 수가(넓이가 아닌) 더 많은 영역을 기준으로 한 색깔로 전부 칠한 후
    1 + 작은 영역의 개수를 더하면 최소값이 출력된다.
*/

import java.util.*;
import java.io.*;

class Boj20365 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        StringTokenizer redSt = new StringTokenizer(line, "B");
        StringTokenizer blueSt = new StringTokenizer(line, "R");
        int red = redSt.countTokens();
        int blue = blueSt.countTokens();
        int count = 1;
        if (red > blue) {
            count += blue;
        } else {
            count += red;
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}
