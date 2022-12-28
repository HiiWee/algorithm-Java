package boj;

import java.io.*;
import java.util.*;

class Boj2800 {
    static String inputLine;
    static List<String> results = new ArrayList<>();
    static String[] brackets;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        inputLine = br.readLine();
        brackets = findBracketIndexes(inputLine);

        backtracking(0, inputLine, brackets.length);
        results.sort(Comparator.naturalOrder());

        StringBuilder resultBuilder = new StringBuilder();
        for (String result : results) {
            resultBuilder.append(result)
                    .append("\n");
        }

        bw.write(resultBuilder.toString());
        bw.flush();
        bw.close();
    }

    public static String[] findBracketIndexes(final String line) {
        List<String> bracketsIndexPairs = new ArrayList<>();
        Stack<Integer> bracketIndexes = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(') {
                bracketIndexes.push(i);
                continue;
            }
            if (c == ')') {
                int start = bracketIndexes.pop();
                bracketsIndexPairs.add(start + " " + i);
            }
        }
        return bracketsIndexPairs.toArray(new String[bracketsIndexPairs.size()]);
    }

    public static void backtracking(int removeIndex, String line, int size) {
        if (removeIndex == size) {
            String trimLine = removeEmptyPlace(line);
            if (!trimLine.equals(inputLine) && !results.contains(trimLine)) {
                results.add(trimLine);
            }
            return;
        }
        backtracking(removeIndex + 1, removeBracket(removeIndex, line), size);
        backtracking(removeIndex + 1, line, size);
    }

    public static String removeBracket(int removeIndex, String line) {
        StringTokenizer st = new StringTokenizer(brackets[removeIndex]);
        int leftBracket = Integer.parseInt(st.nextToken());
        int rightBracket = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder(line);
        sb.replace(leftBracket, leftBracket + 1, " ");
        sb.replace(rightBracket, rightBracket + 1, " ");
        return sb.toString();
    }

    private static String removeEmptyPlace(final String line) {
        return line.replaceAll(" ", "");
    }
}
