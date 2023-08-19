package boj;

// 맞은 풀이
import java.io.*;
import java.util.*;

class Boj22862_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int count = 0;
        int totalMax = 0;
        int currentMax = 0;
        while (r < n) {
            if (count > k) {
                currentMax -= (arr[l] + 1) % 2;
                count -= arr[l++] % 2;
            } else if (arr[r] % 2 == 0) {
                currentMax++;
                r++;
            } else if (arr[r] % 2 == 1) {
                count++;
                r++;
            }
            totalMax = Math.max(totalMax, currentMax);
        }

        bw.write(totalMax + "");
        bw.flush();
        bw.close();
    }
}

// 과거에 풀었던 좀 더 직관적인 풀이
class Boj22862_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;
        int count = 0;
        int maxLength = 0;
        while (r < n) {
            if (count <= k) {
                count += arr[r++] % 2;
            } else {
                count -= arr[l++] % 2;
            }
            if (count <= k) {
                maxLength = Math.max(maxLength, r - l - count);
            }
        }

        bw.write(maxLength + "");
        bw.flush();
        bw.close();
    }
}
