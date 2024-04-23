package boj;

import java.io.*;
import java.util.*;

class Boj12931 {

    static int n;
    static int[] numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        while (true) {
            Arrays.sort(numbers);
            if (numbers[n - 1] == 0) {
                break;
            }

            boolean canDivdeTwo = true;
            for (int i = 0; i < n; i++) {
                if (numbers[i] % 2 == 1) {
                    numbers[i]--;
                    canDivdeTwo = false;
                    break;
                }
            }
            if (canDivdeTwo) {
                for (int i = 0; i < n; i++) {
                    numbers[i] /= 2;
                }
            }
            count++;
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}
