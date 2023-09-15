package boj;

import java.io.*;

class Boj20164 {
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int number = Integer.parseInt(br.readLine());
        recur(number, getOdd(number));

        bw.write(min + " " + max);
        bw.flush();
        bw.close();
    }

    public static void recur(int number, int oddCount) {
        if (number < 10) {
            max = Math.max(oddCount, max);
            min = Math.min(oddCount, min);
        } else if (number < 100) {
            int sum = number / 10 + number % 10;
            recur(sum, getOdd(sum) + oddCount);
        } else {
            String next = Integer.toString(number);
            for (int i = 1; i <= next.length(); i++) {
                for (int j = i + 1; j <= next.length() - 1; j++) {
                    int nextNumber = Integer.parseInt(next.substring(0, i));
                    nextNumber += Integer.parseInt(next.substring(i, j));
                    nextNumber += Integer.parseInt(next.substring(j));
                    recur(nextNumber, getOdd(nextNumber) + oddCount);
                }
            }
        }
    }

    public static int getOdd(int number) {
        int count = 0;
        while (number != 0) {
            if (number % 10 % 2 == 1){
                count++;
            }
            number /= 10;
        }

        return count;
    }
}