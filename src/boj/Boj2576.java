package boj;

import java.util.Scanner;

class Boj2576 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] n = new int[7];
        int total = 0;
        int minOdd = 100;
        for (int i = 0; i < 7; i++) {
            n[i] = sc.nextInt();
            if (n[i] % 2 == 1) {
                total += n[i];
                if (n[i] < minOdd) {
                    minOdd = n[i];
                }
            }
        }

        if (total == 0) {
            System.out.println(-1);
        } else {
            System.out.printf("%d\n%d", total, minOdd);
        }


    }
}
