package boj;

import java.util.Scanner;

class Boj11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1)
                nums[i] = 1 % 10007;
            else if (i == 2) {
                nums[i] = 2 % 10007;
            }
            else {
                nums[i] = (nums[i - 1] + nums[i - 2]) % 10007;
            }
        }
        System.out.println(nums[n]);
    }
}