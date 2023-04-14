package programmers;

public class TwoDifferentBIt {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long n = numbers[i];
            long count = 0;
            while (true) {
                if (n % 2 == 0) {
                    break;
                }
                n /= 2;
                count++;
            }
            long result = numbers[i] + (long) Math.pow(2, count) - (long) Math.pow(2, count - 1);
            System.out.println(Math.pow(2, count - 1));
            answer[i] = result;
        }
        return answer;
    }
}
