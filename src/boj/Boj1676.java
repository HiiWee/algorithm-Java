package boj;

import java.io.*;

// 2와 5를 지나면 0이 하나씩 증가됨 하지만, 2는 5보다 무수히 많으므로
// 결국 5의 숫자가 0이 늘어나는 요인이 된다. 25, 125 추가로 곱해지는 5가 존재함을 주의
class Boj1676_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num % 5 == 0) {
                count++;
                num /= 5;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}

class Boj1676_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int result = 0;
        result += n / 5;
        result += n / 25;
        result += n / 125;

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}