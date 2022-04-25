package programmers;

public class FindRemainingNumberOne {
    public static int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 1) {
                answer += i;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] n = {10 ,12};
        int[] results = {3, 11};
        for (int i = 0; i < n.length; i++) {
            if (solution(n[i]) == results[i]) {
                System.out.println("pass");
            }
        }
    }
}
