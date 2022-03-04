/*
* 프로그래머스 LV01 : 없는 숫자 더하기
* */
package programmers;

public class AddMissingNumber {

    public static int solution(int[] numbers) {
        int answer = 0;
        int[] sum = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < numbers.length; j++)
                if (sum[i] == numbers[j])
                    sum[i] -= numbers[j];
            answer += sum[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 4, 6, 7, 8};
        int answer = solution(numbers);
        System.out.println("answer = " + answer);
    }
}

