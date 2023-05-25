package boj;

import java.io.*;
import java.util.*;

class Boj4256 {
    private static int[] preorders;
    private static int[] inorders;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            preorders = new int[n];
            inorders = new int[n];
            StringTokenizer preorderTokens = new StringTokenizer(br.readLine());
            StringTokenizer inorderTokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorders[i] = Integer.parseInt(preorderTokens.nextToken());
                inorders[i] = Integer.parseInt(inorderTokens.nextToken());
            }
            postorder(0, 0, n - 1, result);
            result.append("\n");
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void postorder(int rootIndex, int start, int end, StringBuilder result) {
        if (start > end) {
            return;
        }
        if (start == end) {
            result.append(preorders[rootIndex]).append(" ");
            return;
        }
        int root = preorders[rootIndex];
        for (int mid = start; mid <= end; mid++) {
            if (root == inorders[mid]) {
                postorder(rootIndex + 1, start, mid - 1, result);
                postorder(rootIndex + 1 + (mid - start), mid + 1, end, result);
                result.append(preorders[rootIndex]).append(" ");
                break;
            }
        }
    }
}