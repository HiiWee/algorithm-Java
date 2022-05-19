package boj;
/*
    1. 에라토네스의 채로 모든 소수를 구하고
    2. 주어진 값 이하의 소수를 돌면서 a, b가 모두 소수인지 판별하고 소수라면 바로 출력하고 다음 값 받음
       끝까지 탐색하지 않는 이유는 b - a값이 최대가 되기 위해선 a, b값 모두 소수라면 최초에 나온 값을 이용해야 한다.
*/
import java.io.*;

class Boj6588 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 1. 모든 소수 구하기
        int[] arr = new int[1000001];
        // 홀수 소수 이므로 짝수 소수인 2 제외
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            if (arr[i] == 1) {
                continue;
            }
            for (int j = i * 2; j <= 1000000; j += i) {
                arr[j] = 1;
            }
        }
        int n = Integer.parseInt(br.readLine());
        while (n != 0) {
            for (int i = 3; i <= n - i; i++) {
                // a, b가 소수인 조건을 만족한다면 바로 출력 후 break
                if (arr[i] == 0 && arr[n - i] == 0) {
                    bw.write(n + " = " + i + " + " + (n - i) + "\n");
                    break;
                }
            }
            n = Integer.parseInt(br.readLine());
        }
        bw.flush();
        bw.close();

    }
}