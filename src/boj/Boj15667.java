package boj;

/*
    K값이 100일때 최대 N값인 10101이 되므로 1 ~ 100의 숫자를 완전탐색하며
    N개의 폭죽값이 나타나는 K값을 출력하면 된다.
*/
import java.io.*;

class Boj15667 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int result = 1;
        for (int i = 1; i <= 100; i++) {
            int count = 1;
            count += i;
            count += i * i;

            if (count == n) {
                result = i;
                break;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}