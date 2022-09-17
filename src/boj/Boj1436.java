package boj;

/*
    단순무식하게 하나씩 카운팅하며 검사해보자!
*/

import java.io.*;

class Boj1436 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        int num = 0;
        while (true) {
            num++;
            if (check(num)) {
                count++;
            }
            if (count == n) {
                break;
            }
        }

        bw.write(num + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean check(int num) {
        String str = String.valueOf(num);
        int count = 0;
        for (int i = 0; i < str.length() - 2; i++) {
            int num1 = str.charAt(i) - '0';
            int num2 = str.charAt(i + 1) - '0';
            int num3 = str.charAt(i + 2) - '0';
            if (num1 == 6 && num2 == 6 && num3 == 6) {
                return true;
            }
        }
        return false;
    }
}
