package boj;

import java.io.*;
import java.util.*;

class Boj7490 {

    static final String[] opSymbols = {" ", "+", "-"};
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] number = new int[n];
            for (int i = 0; i < n; i++) {
                number[i] = i + 1;
            }
            search(number, new int[n - 1], 0,  n - 1);
            answer.append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int[] number, int[] op, int index, int limit) {
        if (index == limit) {
            if (calculate(number, op) == 0) {
                StringBuilder result = new StringBuilder();
                result.append("1");
                for (int i = 0; i < op.length; i++) {
                    result.append(opSymbols[op[i]]).append(number[i + 1]);
                }
                answer.append(result).append("\n");
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            op[index] = i;
            search(number, op, index + 1, limit);
        }
    }

    public static int calculate(int[] number, int[] op) {
        int result = 0;
        for (int i = 0; i < op.length; i++) {
            if (op[i] == 0) {
                if (i == 0) {
                    result = number[i] * 10 + number[i + 1];
                } else {
                    if (op[i - 1] == 1) {
                        result -= number[i];
                        result += number[i] * 10 + number[i + 1];
                    } else if (op[i - 1] == 2) {
                        result += number[i];
                        result -= number[i] * 10 + number[i + 1];
                    } else {
                        result = result * 10 + number[i + 1];
                    }
                }
            } else if (op[i] == 1) {
                if (i == 0) {
                    result = number[i] + number[i + 1];
                } else {
                    result += number[i + 1];
                }
            } else {
                if (i == 0) {
                    result = number[i] - number[i + 1];
                } else {
                    result -= number[i + 1];
                }
            }
        }

        return result;
    }
}
