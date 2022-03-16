package programmers;

public class NumericAndEnglishWord {
    public static int solution(String s) {
        int answer = 0;
        String[] englishNum = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(englishNum[i], String.valueOf(i));
        }
        answer = Integer.parseInt(s);

        return answer;
    }

    public static void main(String[] args) {
        String[] englishWords = {"one4seveneight", "23four5six7", "2three45sixseven", "123"};

        for (String englishWord : englishWords) {
            int result = solution(englishWord);
            System.out.println(englishWord + " -> " + result);
        }
    }
}
