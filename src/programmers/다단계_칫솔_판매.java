package programmers;

import java.util.*;

class 다단계_칫솔_판매 {

    Map<String, String> trees = new HashMap<>();
    Map<String, Integer> priceSum = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        makeTrees(enroll, referral);

        for (int i = 0; i < seller.length; i++) {
            dividePrice(seller[i], amount[i] * 100);
        }
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = priceSum.get(enroll[i]);
        }
        return answer;
    }

    public void dividePrice(String seller, int price) {
        while (!seller.equals("-") && price >= 1) {
            int curPrice = (int) (price * (10.0 / 100));
            priceSum.put(seller, priceSum.get(seller) + (price - curPrice));
            seller = trees.get(seller);
            price = curPrice;
        }
    }

    public void makeTrees(String[] enroll, String[] referral) {
        for (int i = 0; i < enroll.length; i++) {
            trees.put(enroll[i], referral[i]);
            priceSum.put(enroll[i], 0);
        }
    }
}