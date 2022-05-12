package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Boj10824 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");
        long num1 = Long.parseLong(nums[0] + nums[1]);
        long num2 = Long.parseLong(nums[2] + nums[3]);

        System.out.println(num1 + num2);
    }
}
