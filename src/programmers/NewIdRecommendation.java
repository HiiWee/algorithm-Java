package programmers;

class NewIdRecommendation {
    public static String solution(String new_id) {
        String answer = "";
        int length, count;
        char letter;
        // 1단계: 모든 대문자 -> 소문자 치환
        answer = new_id.toLowerCase();
        StringBuilder new_answer = new StringBuilder("");

        // 2단계: 소문자, 숫자, -, ., _를 제외한 모든 문자를 제거
        length = answer.length();
        for (int i = 0; i < length; i++) {
            letter = answer.charAt(i);
            if (letter >= 97 && letter <= 122)
                new_answer.append(answer.charAt(i));
            else if (letter >= 48 && letter <= 57)
                new_answer.append(answer.charAt(i));
            else if (letter == 45 || letter == 46 || letter == 95)
                new_answer.append(answer.charAt(i));
        }
        // 3단계: .이 2번 이상 연속된 부분을 하나의 마침표(.)로 치환
        length = new_answer.length();
        char currentLetter, nextLetter;
        count = 0;
        for (int i = 0; i < length; i++) {

            if (i != length - 1) {
                currentLetter = new_answer.charAt(i);
                nextLetter = new_answer.charAt(i + 1);
            } else {
                break;
            }
            if (currentLetter == 46 && i != length - 1) {
                if (nextLetter == 46)
                    new_answer.replace(i, i + 1, " ");
                count++;
            }
        }
        answer = new_answer.toString();
        answer = answer.replace(" ", "");
        new_answer.delete(0, new_answer.length());
        new_answer.append(answer);


        // 4단계: 마침표가 처음이나 끝에 위치한다면 제거한다.
        if (new_answer.charAt(0) == 46)
            new_answer.deleteCharAt(0);
        if (new_answer.length() != 0) {
            if (new_answer.charAt(new_answer.length() - 1) == 46)
                new_answer.deleteCharAt(new_answer.length() - 1);
        }

        // 5단계: 빈 문자열이라면 "a"대입
        if (new_answer.length() == 0)
            new_answer.append("a");

        // 6단계: 길이가 15 초과시 앞에서 15개 제외 나머지 문자 제거
        // 이후 끝 문자가 '.'이면 이 또한 제거
        if (new_answer.length() > 15) {
            count = new_answer.length() - 15;
            for (int i = 0; i < count; i++)
                new_answer.deleteCharAt(15);
            if (new_answer.charAt(14) == '.')
                new_answer.deleteCharAt(14);
        }

        // 7단계: 길이가 2자 이하면 마지막문자를 길이가 3이 될때까지 반복해서 끝에 붙임
        if (new_answer.length() < 3) {
            count = 3 - new_answer.length();
            char lastLetter = new_answer.charAt(new_answer.length() - 1);
            for (int i = 0; i < count; i++)
                new_answer.append(lastLetter);
        }
        answer = new_answer.toString();
        return answer;
    }


    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        String change_id = solution(new_id);
        System.out.println("change_id = " + change_id);
    }
}
