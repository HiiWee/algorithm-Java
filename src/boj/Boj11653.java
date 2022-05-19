package boj;

import java.io.*;

// 에라토네스의 채 이용한 정석적인 느낌(시간 오래걸림..)
class Boj11653_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            return;
        }
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (arr[i] == 1) {
                continue;
            }
            for (int j = 2 * i; j <= n; j += i) {
                arr[j] = 1;
            }
        }
        System.out.println("pass");
        while (n > 1) {
            for (int i = 2; i < arr.length; i++) {
                if (arr[i] == 0 && n % i == 0) {
                    bw.write(i + "\n");
                    n = n / i;
                    System.out.println("pass2");
                    break;
                }
            }
        }
        bw.flush();
        bw.close();
    }
}

// 매우 간단, i를 n까지 돌며 n이 i로 나누어 떨어지면 계속 출력하면서 n값을 줄여감
class Boj11653_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            return;
        }
        for (int i = 2; i <= n; i++) {
            while(n % i == 0) {
                bw.write(i + "\n");
                n /= i;
            }
        }
        bw.flush();
        bw.close();
    }
}