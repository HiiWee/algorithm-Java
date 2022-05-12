package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        String[] sepWord = new String[word.length()];

        for (int i = 0; i < word.length(); i++) {
            sepWord[i] = word.substring(i);
        }
        Arrays.sort(sepWord);
        for (int i = 0; i < word.length(); i++) {
            System.out.println(sepWord[i]);
        }
    }
}