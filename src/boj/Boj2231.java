package boj;

/*
    단순하게 1 ~ N까지 돌면서 생성자를 일일이 구한다.
    만약 N까지 돌아도 존재하지 않는다면 0 출력
*/

import java.io.*;

class Boj2231 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int num = getSumOfDecomposition(i);
            if (num == n) {
                result = i;
                break;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static int getSumOfDecomposition(int num) {
        int mod = num;
        int sum = 0;
        while (true) {
            sum += mod % 10;
            mod /= 10;
            if (mod == 0) {
                break;
            }
        }
        return num + sum;
    }
}