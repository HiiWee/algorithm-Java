package boj;

import java.io.*;
import java.util.*;

class Boj6987 {

    static int[] result = new int[18];
    static int[][] compare = {
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4},
            {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5}
    };
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            boolean isInvalidInput = false;
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sum = 0;
            for (int j = 0; j < 18; j++) {
                result[j] = Integer.parseInt(st.nextToken());
                sum += result[j];
                if ((j + 1) % 3 == 0) {
                    if (sum != 5) {
                        isInvalidInput = true;
                    }
                    sum = 0;
                }
            }

            if (isInvalidInput) {
                answer.append("0\n");
                continue;
            }

            flag = false;
            search(0, result);
            if (flag) {
                answer.append("1\n");
            } else {
                answer.append("0\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] result) {
        if (index == 15) {
            flag = true;
            return;
        }

        int left = compare[0][index];
        int right = compare[1][index];
        // left 승
        if (result[3 * left] > 0 && result[3 * right + 2] > 0) {
            result[3 * left]--;
            result[3 * right + 2]--;
            search(index + 1, result);
            result[3 * left]++;
            result[3 * right + 2]++;
        }
        // 무승부
        if (result[3 * left + 1] > 0 && result[3 * right + 1] > 0) {
            result[3 * left + 1]--;
            result[3 * right + 1]--;
            search(index + 1, result);
            result[3 * left + 1]++;
            result[3 * right + 1]++;
        }
        // left 패
        if (result[3 * left + 2] > 0 && result[3 * right] > 0) {
            result[3 * left + 2]--;
            result[3 * right]--;
            search(index + 1, result);
            result[3 * left + 2]++;
            result[3 * right]++;
        }
    }
}
