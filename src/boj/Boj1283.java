package boj;

import java.io.*;

public class Boj1283 {


    static int n;
    static String[] words;
    static boolean[] isUsed;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        words = new String[n];
        isUsed = new boolean[26];

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();

            boolean canUse = false;
            int index = -1;
            // 1. 각 단어의 첫 글자 검사
            String[] tokens = words[i].split(" ");
            for (int j = 0; j < tokens.length; j++) {
                if (canUse(tokens[j].toLowerCase().charAt(0))) {
                    isUsed[tokens[j].toLowerCase().charAt(0) - 'a'] = true;
                    canUse = true;
                    index = j;
                    break;
                }
            }
            // 1-1. 각 단어의 첫글자 중 하나를 사용할 수 있다면 사용함
            if (canUse) {
                for (int j = 0; j < tokens.length; j++) {
                    if (j == index) {
                        answer.append("[")
                                .append(tokens[j].charAt(0))
                                .append("]")
                                .append(tokens[j].substring(1))
                                .append(" ");
                    } else {
                        answer.append(tokens[j]).append(" ");
                    }
                }
                answer.append("\n");
            } else {
                // 2. 각 단어의 첫 글자를 전부 찾아도 없다면 모든 알파벳을 뒤져봄
                boolean isFind = false;
                String lowerWord = words[i].toLowerCase();
                // 2-1. 앞에서부터 공백이 아니고, 사용하지 않은 알파벳이라면 바로 사용함
                for (int j = 0; j < words[i].length(); j++) {
                    if (!isFind && words[i].charAt(j) != ' ' && !isUsed[lowerWord.charAt(j) - 'a']) {
                        isUsed[lowerWord.charAt(j) - 'a'] = true;
                        isFind = true;
                        answer.append("[").append(words[i].charAt(j)).append("]");
                    } else {
                        answer.append(words[i].charAt(j));
                    }
                }
                answer.append("\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }

    public static boolean canUse(char c) {
        return !isUsed[c - 'a'];
    }

}
