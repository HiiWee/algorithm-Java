package boj;

import java.io.*;

class Boj17413 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '<') {
                appendWord(result, word);
                int closedBracketIndex = line.indexOf('>', i);
                result.append(line.substring(i, closedBracketIndex + 1));
                i = closedBracketIndex;
            } else if (c == ' ') {
                appendWord(result, word);
                result.append(c);
            } else {
                word.append(c);
            }
        }
        appendWord(result, word);

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    private static void appendWord(final StringBuilder result, final StringBuilder word) {
        if (word.length() > 0) {
            result.append(word.reverse());
            word.setLength(0);
        }
    }
}
