package boj;

import java.io.*;
import java.util.*;

class Boj15659 {

    static int n;
    static int[] number;
    static int[] op = new int[4];
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }


        search(1, 0, number[0]);

        bw.write(max + "\n" + min);
        bw.flush();
        bw.close();
    }

    public static void search(int index, int sum, int previous) {
        if (index == n) {
            int result = sum + previous;
            min = Math.min(result, min);
            max = Math.max(result, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;
                if (i == 0) {
                    search(index + 1, sum + previous, number[index]);
                } else if (i == 1) {
                    search(index + 1, sum + previous, -number[index]);
                } else if (i == 2) {
                    search(index + 1, sum, previous * number[index]);
                } else {
                    search(index + 1, sum, previous / number[index]);
                }
                op[i]++;
            }
        }
    }
}

