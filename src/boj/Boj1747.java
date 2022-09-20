package boj;
/*
    에라토스테네스의 채를 이용해 1 ~ 1000000까지의 소수를 구해놓고
    n ~ 1000000까지의 수 중에서 첫번째로 나타나는 팰린드론 수를 구한다.
*/

import java.io.*;

class Boj1747 {
    static boolean[] visited = new boolean[1100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        sieve();

        int num = 0;
        for (int i = n; i <= 1100000; i++) {
            if (!visited[i] && checkPalindrome(i)) {
                num = i;
                break;
            }
        }

        bw.write(num + "\n");
        bw.flush();
        bw.close();
    }

    public static void sieve() {
        visited[1] = true;

        for (int i = 2; i <= 1100000; i++) {
            if (visited[i]) {
                continue;
            }
            for (int j = 2 * i; j <= 1100000; j += i) {
                visited[j] = true;
            }
        }
    }

    public static boolean checkPalindrome(int num) {
        String line = String.valueOf(num);
        int left = 0;
        int right= line.length() - 1;

        while (left < right) {
            if (line.charAt(left) != line.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}