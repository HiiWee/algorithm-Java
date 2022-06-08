package programmers;

public class DartGame {
    static final char SINGLE = 'S';
    static final char DOUBLE = 'D';
    static final char TRIPLE = 'T';
    static final char STAR = '*';

    public static int solution(String dartResult) {
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        int[] numbers = new int[3];
        int index = 0;

        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);

            if (c >= 48 && c <= 57) {
                sb.append(c);
            } else {
                int num = 0;
                if (sb.length() > 0) {
                    num = Integer.parseInt(sb.toString());
                    sb.setLength(0);
                }
                if (c == SINGLE) {
                    numbers[index] = num;
                    index++;
                } else if (c == DOUBLE) {
                    numbers[index] = (int) Math.pow(num, 2);
                    index++;
                } else if (c == TRIPLE) {
                    numbers[index] = (int) Math.pow(num, 3);
                    index++;
                } else if (c == STAR) {
                    if (index > 1) {
                        numbers[index - 1] *= 2;
                        numbers[index - 2] *= 2;
                    } else {
                        numbers[index - 1] *= 2;
                    }
                } else {
                    numbers[index - 1] *= -1;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            answer += numbers[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] dartResult = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*"};
        int[] answer = {37, 9, 3, 23, 5, -4, 59};

        for (int i = 0; i < dartResult.length; i++) {
            int result = solution(dartResult[i]);
            if (result == answer[i]) {
                System.out.println("Test " + (i + 1) + " is passed");
            }
        }
    }
}
