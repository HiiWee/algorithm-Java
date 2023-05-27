package boj;

import java.io.*;
import java.util.*;

class Boj2263_1 {
    private static int[] inorders;
    private static int[] postorders;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        inorders = new int[n];
        postorders = new int[n];

        StringTokenizer inorderToken = new StringTokenizer(br.readLine());
        StringTokenizer postorderToken = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorders[i] = Integer.parseInt(inorderToken.nextToken());
            postorders[i] = Integer.parseInt(postorderToken.nextToken());
        }

        preorder(n - 1, 0, n - 1);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void preorder(int rootIndex, int start, int end) {
        if (start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            if (inorders[i] == postorders[rootIndex]) {
                result.append(postorders[rootIndex]).append(" ");
                preorder(rootIndex - 1 - (end - i), start, i - 1);
                preorder(rootIndex - 1, i + 1, end);
                break;
            }
        }
    }
}


class Boj2263_2 {
    private static int[] inorders;
    private static int[] postorders;
    private static int[] inorderPosition;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        inorders = new int[n];
        postorders = new int[n];
        inorderPosition = new int[n + 1];

        StringTokenizer inorderToken = new StringTokenizer(br.readLine());
        StringTokenizer postorderToken = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorders[i] = Integer.parseInt(inorderToken.nextToken());
            postorders[i] = Integer.parseInt(postorderToken.nextToken());
        }
        for (int i = 0; i < n; i++) {
            inorderPosition[inorders[i]] = i;
        }

        preorder(n - 1, 0, n - 1);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void preorder(int rootIndex, int start, int end) {
        if (start > end) {
            return;
        }
        int rootIndexPosition = inorderPosition[postorders[rootIndex]];
        result.append(postorders[rootIndex]).append(" ");
        preorder(rootIndex - 1 - (end - rootIndexPosition), start, rootIndexPosition - 1);
        preorder(rootIndex - 1, rootIndexPosition + 1, end);

    }
}