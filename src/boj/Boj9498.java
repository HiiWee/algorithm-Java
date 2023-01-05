package boj;

// 시험 점수를 입력받아 90 ~ 100점은 A,
// 80 ~ 89점은 B,
// 70 ~ 79점은 C,
// 60 ~ 69점은 D,
// 나머지 점수는 F를 출력하는 프로그램을 작성하시오.

import java.io.*;

class Boj9498 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int score = Integer.parseInt(br.readLine());

        if (score >= 90 && score <= 100) {
            bw.write("A");
        } else if (score >= 80 && score <= 89) {
            bw.write("B");
        } else if (score >= 70 && score <= 79) {
            bw.write("C");
        } else if (score >= 60 && score <= 69) {
            bw.write("D");
        } else {
            bw.write("F");
        }
        bw.flush();
        bw.close();
    }
}


