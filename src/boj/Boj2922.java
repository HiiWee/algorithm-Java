package boj;

import java.io.*;

class TODO2922 {

    static int[] check = new int[26];
    static {
        check[0] = 1;
        check[4] = 1;
        check[8] = 1;
        check[14] = 1;
        check[20] = 1;
    }
    static char[] word;
    static long count;
    static boolean hasL;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        word = line.toCharArray();
        for (char c : word) {
            if (c == 'L') {
                hasL = true;
                break;
            }
        }

        // 일단 자리에 들어갈 수 있는 수를 모두 구하기 (자음은 1, 모음은 2)
        int[] out = new int[word.length];
        search(0, out);

        bw.write(Long.toString(count));
        bw.flush();
        bw.close();
    }

    public static void search(int index, int[] out) {
        if (index == word.length) {
            long current;
            int countA = 0;
            int countB = 0;
            if (hasL) {
                for (int n : out) {
                    if (n == 1) {
                        countB++;
                    } else if (n == 2) {
                        countA++;
                    }
                }
                current = (long) Math.pow(5, countA) * (long) Math.pow(21, countB);
            } else {
                for (int n : out) {
                    if (n == 1) {
                        countB++;
                    } else if (n == 2) {
                        countA++;
                    }
                }
                if (countB == 0) {
                    return;
                }
                System.out.println(countA + " " + countB);
                current = countB * (long) Math.pow(5, countA) * (long) Math.pow(21, countB - 1);
            }
            count += current;
            return;
        }

        if (word[index] != '_') {
            search(index + 1, out);
            return;
        }

        word[index] = 'A';
        if (isPossible(index, word)) {
            out[index] = 2;
            word[index] = 'A';
            search(index + 1, out);
            word[index] = '_';
        }
        word[index] = 'B';
        if (isPossible(index, word)) {
            out[index] = 1;
            word[index] = 'B';
            search(index + 1, out);
            word[index] = '_';
        }
    }

    public static boolean isPossible(int index, char[] word) {
        // 자음, 모음 3개 연속인지 검사
        int count = 0;
        if (index - 2 >= 0) {
            for (int i = index - 2; i < index + 1; i++) {
                count += check[word[i] - 'A'];
            }
            if (count == 0 || count == 3) {
                return false;
            }
        }
        if (index - 1 >= 0 && index + 1 < word.length) {
            count = 0;
            for (int i = index - 1; i < index + 2; i++) {
                if (word[i] == '_') {
                    return true;
                }
                count += check[word[i] -'A'];
            }
            if (count == 0 || count == 3) {
                return false;
            }
        }
        if (index + 2 < word.length) {
            count = 0;
            for (int i = index; i < index + 3; i++) {
                if (word[i] == '_') {
                    return true;
                }
                count += check[word[i] - 'A'];
            }
            if (count == 0 || count == 3) {
                return false;
            }
        }

        return true;
    }

}
