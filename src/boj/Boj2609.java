package boj;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

class Boj2609 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[2];
        nums[0] = Integer.parseInt(st.nextToken());
        nums[1] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        int num = nums[0];
        int mod = nums[1];
        while(true) {
            int result = num % mod;

            if (result == 0) {
                break;
            }
            num = mod;
            mod = result;
        }
        bw.write(mod + "\n");
        bw.write((nums[0] * nums[1]) / mod + "\n");
        bw.flush();
        bw.close();
    }
}