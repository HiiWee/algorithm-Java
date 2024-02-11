package programmers;

class 카펫 {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow;

        for (int i = 1; i <= total; i++) {
            int guessingValue = -1;
            if (total % i == 0) {
                guessingValue = 2 * i + 2 * (total / i) - 4;
            }
            if (guessingValue == brown) {
                answer[1] = i;
                answer[0] = total / i;
                break;
            }
        }
        return answer;
    }
}