package boj;
/*
input : 5
output :
    *
   * *
  *   *
 *     *
*********
*/
import java.util.Scanner;

class Boj10992 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    System.out.print("*");
                } else {
                    if (i > 0 && i < n - 1) {
                        System.out.print(" ");
                    } else if (i == n - 1) {
                        System.out.print("*");
                    }
                }
            }
            for (int j = 0; j <= i - 1; j++) {
                if (j == i - 1) {
                    System.out.print("*");
                } else {
                    if (i < n - 1) {
                        System.out.print(" ");
                    } else if (i == n - 1) {
                        System.out.print("*");
                    }
                }
            }
            System.out.println();
        }
    }
}