package boj;

import java.io.*;
import java.util.*;

class Boj7682 {

    static final char[] ORDER = {'X', 'O'};

    static String line;
    static Set<String> answers = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder answer = new StringBuilder();
        search(0, 0, ".........".toCharArray());

        while (!(line = br.readLine()).equals("end")) {
            if (answers.contains(line)) {
                answer.append("valid\n");
            } else {
                answer.append("invalid\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static void search(int count, int sequence, char[] out) {
        if (isEnd(out) || count == 9) {
            answers.add(new String(out));
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (out[i] == '.') {
                out[i] = ORDER[sequence % 2];
                search(count + 1, sequence + 1, out);
                out[i] = '.';
            }
        }
    }

    public static boolean isEnd(char[] out) {
        if (out[0] != '.' && out[0] == out[1] && out[0] == out[2]) {
            return true;
        }
        if (out[3] != '.' && out[3] == out[4] && out[3] == out[5]) {
            return true;
        }
        if (out[6] != '.' && out[6] == out[7] && out[6] == out[8]) {
            return true;
        }
        if (out[0] != '.' && out[0] == out[3] && out[0] == out[6]) {
            return true;
        }
        if (out[1] != '.' && out[1] == out[4] && out[1] == out[7]) {
            return true;
        }
        if (out[2] != '.' && out[2] == out[5] && out[2] == out[8]) {
            return true;
        }
        if (out[0] != '.' && out[0] == out[4] && out[0] == out[8]) {
            return true;
        }
        return out[2] != '.' && out[2] == out[4] && out[2] == out[6];
    }
}
