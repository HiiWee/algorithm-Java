package boj;
/* input : 3
outut:
  *
 * *
* * *
*/
import java.util.Scanner;
class Boj10991 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                if (j < i) {
                    System.out.print("* ");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
