package boj;

import java.io.*;
import java.util.*;

class Boj19949 {

    static int[] numbers = new int[10];
    static int totalCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        search(new int[10], 0, 0);

        bw.write(Integer.toString(totalCount));
        bw.flush();
        bw.close();
    }

    public static void search(int[] out, int index, int correctCount) {
        if (index > 0 && out[index - 1] == numbers[index - 1]) {
            correctCount++;
        }
        if (index == 10) {
            if (correctCount >= 5) {
                totalCount++;
            }
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (index < 2 || isPossible(i, out, index)) {
                out[index] = i;
                search(out, index + 1, correctCount);
            }
        }
    }

    public static boolean isPossible(int number, int[] out, int index) {
        return out[index - 1] != number || out[index - 2] != number;
    }
}

