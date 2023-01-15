package boj;

import java.io.*;
import java.util.*;

class Boj10807 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        int findNumber = Integer.parseInt(br.readLine());

        int count = (int) Arrays.stream(numbers)
                .filter(number -> number == findNumber)
                .count();
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }
}