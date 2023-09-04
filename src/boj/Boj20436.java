package boj;

import java.io.*;
import java.util.*;

class Boj20436 {
    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int calcDistance(Pos pos) {
            return Math.abs(this.r - pos.r) + Math.abs(this.c - pos.c);
        }
    }
    private static char[][] keyboards = {
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
            {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';'},
            {'z', 'x', 'c', 'v', 'b', 'n', 'm', ';', ';', ';'}
    };
    private static char[] consonants = {'q', 'w', 'e', 'r', 't', 'a', 's', 'd', 'f', 'g', 'z', 'x', 'c', 'v'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String firstLine = br.readLine();
        char left = firstLine.charAt(0);
        char right = firstLine.charAt(2);
        String line = br.readLine();

        Pos leftPos = findPos(left);
        Pos rightPos = findPos(right);

        int second = line.length();
        for (char keypad : line.toCharArray()) {
            Pos pos = findPos(keypad);
            int leftDistance = leftPos.calcDistance(pos);
            int rightDistance = rightPos.calcDistance(pos);

            if (isConsonant(keypad)) {
                leftPos.r = pos.r;
                leftPos.c = pos.c;
                second += leftDistance;
            } else {
                rightPos.r = pos.r;
                rightPos.c = pos.c;
                second += rightDistance;
            }
        }

        bw.write(second + "");
        bw.flush();
        bw.close();
    }

    public static Pos findPos(char keypad) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (keyboards[i][j] == keypad) {
                    return new Pos(i, j);
                }
            }
        }
        return null;
    }

    public static boolean isConsonant(char keypad) {
        for (char consonant : consonants) {
            if (keypad == consonant) {
                return true;
            }
        }
        return false;
    }
}