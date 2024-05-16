package boj;

import java.io.*;
import java.util.*;

class Main {

    static int n;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        search(1, 2);
        search(1, 3);
        search(1, 5);
        search(1, 7);

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int index, int number) {
        if (index == n) {
            answer.append(number).append('\n');
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (isPrime(number * 10 + i)) {
                search(index + 1, number * 10 + i);
            }
        }
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < (int) Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
