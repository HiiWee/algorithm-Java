package boj;

import java.io.*;

class Boj2138 {

    static int n;
    static String end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        char[] start = br.readLine().toCharArray();
        char[] secondStart = new char[n];
        for (int i = 0; i < n; i++) {
            secondStart[i] = start[i];
        }
        end = br.readLine();
        int answer = Integer.MAX_VALUE;

        // 0번 전구를 누르지 않고 검사
        int firstCount = startFlip(start);
        if (end.equals(String.valueOf(start))) {
            answer = firstCount;
        }

        // 0번 전구를 누르고 검사
        flip(secondStart, 0);
        int secondCount = startFlip(secondStart) + 1;

        if (end.equals(String.valueOf(secondStart))) {
            answer = Math.min(answer, secondCount);
        }

        if (answer == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(answer));
        }
        bw.flush();
        bw.close();
    }

    private static int startFlip(char[] arr) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] != end.charAt(i - 1)) {
                flip(arr, i);
                count++;
            }
        }
        return count;
    }

    private static void flip(char[] arr, int index) {
        for (int i = index - 1; i <= index + 1; i++) {
            if (i < 0 || i >= arr.length) {
                continue;
            }
            if (arr[i] == '0') {
                arr[i] = '1';
            } else {
                arr[i] = '0';
            }
        }
    }
}

