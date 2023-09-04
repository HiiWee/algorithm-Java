package boj;

import java.io.*;
import java.util.*;

class Boj12933 {
    private static final char[] QUACK = {'q', 'u', 'a', 'c', 'k'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] totalQuacks = br.readLine().toCharArray();

        int minCount = 0;

        for (int i = 0; i < totalQuacks.length; i++) {
            if (totalQuacks[i] == 'q') {
                int[] foundIndex = {-1, -1, -1, -1, -1};
                int count = 0;
                int quackIndex = 0;
                int j = i;

                while (j < totalQuacks.length) {
                    if (totalQuacks[j] == QUACK[quackIndex]) {
                        foundIndex[quackIndex] = j;
                        j++;
                        quackIndex++;
                    } else {
                        j++;
                    }
                    if (quackIndex == 5) {
                        count++;
                        for (int index : foundIndex) {
                            totalQuacks[index] = ' ';
                        }
                        quackIndex = 0;
                        Arrays.fill(foundIndex, -1);
                    }
                }

                if (quackIndex > 0) {
                    for (int k = 0; k < quackIndex; k++) {
                        totalQuacks[foundIndex[k]] = QUACK[k];
                    }
                }

                if (count > 0) {
                    minCount++;
                }

            }
        }
        for (char c : totalQuacks) {
            if (c != ' ') {
                minCount = 0;
                break;
            }
        }

        if (minCount == 0) {
            bw.write("-1");
        } else {
            bw.write(minCount + "");
        }
        bw.flush();
        bw.close();
    }
}