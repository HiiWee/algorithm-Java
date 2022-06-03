package boj;

import java.io.*;
import java.util.*;

class Boj10815 {
    static int[] deck;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        deck = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            deck[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(deck);

        int m = Integer.parseInt(br.readLine());
        int[] cards = new int[m];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            sb.append(lowerBound(cards[i])).append(" ");
        }

        bw.write(sb + "\n");
        bw.flush();
        bw.close();
    }

    public static int lowerBound(int key) {
        int low = 0;
        int high = deck.length - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;

            if (deck[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (deck[low] == key) {
            return 1;
        } else {
            return 0;
        }
    }
}