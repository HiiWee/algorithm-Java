package boj;

import java.util.Scanner;

class Boj2445 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < 2 * n - 1; i++) {
            if (i < n) {
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
                for (int j = 0; j < n - i - 1; j++) {
                    System.out.print("  ");
                }
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
            } else {
                for (int j = i; j < 2 * n - 1; j++) {
                    System.out.print("*");
                }
                for (int j = n - 1; j < i; j++) {
                    System.out.print("  ");
                }
                for (int j = i; j < 2 * n - 1; j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
