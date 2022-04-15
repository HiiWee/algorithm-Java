package programmers;

import java.util.Arrays;

// 1은 예외처리 가능
// 마지막에 존재하는 숫자는 무조건 0초
class StockPrice {
    public static int[] solution(int[] prices) {
        int[] arr = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] == 1 || j == prices.length - 1) {
                    arr[i] = prices.length - i - 1;
                    break;
                } else if (prices[j] < prices[i]) {
                    arr[i] = j - i;
                    break;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        int[] results = {4, 3, 1, 1, 0};

        int[] test = solution(prices);
        
        System.out.println("test = " + Arrays.toString(test));
        System.out.println("results = " + Arrays.toString(results));

    }
}
