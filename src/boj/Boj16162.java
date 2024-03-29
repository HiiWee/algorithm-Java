package boj;

import java.io.*;
import java.util.*;

class Boj16162 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int currentNumber = a;
        int count = 0;
        for (int number : numbers) {
            if (number == currentNumber) {
                count++;
                currentNumber += d;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
}
