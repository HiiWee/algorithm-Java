package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj2562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] numbers = new int[9];
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            if (max < numbers[i]) {
                index = i;
                max = numbers[i];
            }
        }
        bw.write(max + "\n" + (index + 1));
        bw.flush();
        bw.close();
    }
}
