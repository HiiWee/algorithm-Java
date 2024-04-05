package boj;

import java.io.*;

class Boj17615 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        char[] line = br.readLine().toCharArray();
        int answer = Integer.MAX_VALUE;

        int red = 0;
        int blue = 0;
        for (char c : line) {
            if (c == 'R') {
                red++;
            } else {
                blue++;
            }
        }

        // 좌 -> 우 빨강
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (line[i] == 'R') {
                count++;
            } else {
                break;
            }
        }
        answer = Math.min(answer, red - count);

        // 좌 -> 우 파랑
        count = 0;
        for (int i = 0; i < n; i++) {
            if (line[i] == 'B') {
                count++;
            } else {
                break;
            }
        }
        answer = Math.min(answer, blue - count);

        // 우 -> 좌 빨강
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (line[i] == 'R') {
                count++;
            } else {
                break;
            }
        }
        answer = Math.min(answer, red - count);

        // 우 -> 좌 파랑
        count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (line[i] == 'B') {
                count++;
            } else {
                break;
            }
        }
        answer = Math.min(answer, blue - count);

        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }
}
