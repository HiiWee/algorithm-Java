package boj;

import java.util.*;
import java.io.*;

class Boj21315 {
    static int n;
    static int[] card;
    static int[] compare;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        card = new int[n];
        compare = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            compare[i] = Integer.parseInt(st.nextToken());
        }

        for (int k1 = 1; (int) Math.pow(2, k1) <= n; k1++) {
            for (int k2 = 1; (int) Math.pow(2, k2) <= n; k2++) {
                for (int i = 0; i < n; i++) {
                    card[i] = i + 1;
                }

                solve(k1);
                solve(k2);

                boolean flag = true;
                for (int i = 0; i < n; i++) {
                    if (card[i] != compare[i]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    bw.write(k1 + " " + k2);
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
    }

    public static void solve(int k) {
        int range = n;
        for (int i = 1; i <= k + 1; i++) {
            int count = (int) Math.pow(2, k - i + 1);
            mixCard(range, count);
            range = count;
        }
    }

    public static void mixCard(int range, int count) {
        int[] temp = new int[n];
        int index = 0;
        for (int i = range - count; i < range; i++) {
            temp[index++] = card[i];
            card[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            if (card[i] != 0) {
                temp[index++] = card[i];
            }
            card[i] = temp[i];
        }

    }
}