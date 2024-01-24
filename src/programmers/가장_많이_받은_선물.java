package programmers;

import java.util.*;

class 가장_많이_받은_선물 {

    int[][] giftCount;
    Map<String, Integer> nameIndex = new HashMap<>();
    int[] giftAmount;
    int[] presentCount;

    public int solution(String[] friends, String[] gifts) {
        giftCount = new int[friends.length][friends.length];
        giftAmount = new int[friends.length];
        presentCount = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            nameIndex.put(friends[i], i);
        }

        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            String a = st.nextToken();
            String b = st.nextToken();
            giftCount[nameIndex.get(a)][nameIndex.get(b)]++;
        }

        for (int i = 0; i < friends.length; i++) {
            for (int j = 0; j < friends.length; j++) {
                giftAmount[i] += giftCount[i][j];
                giftAmount[i] -= giftCount[j][i];
            }
        }

        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (giftCount[i][j] > giftCount[j][i]) {
                    presentCount[i]++;
                } else if (giftCount[i][j] < giftCount[j][i]) {
                    presentCount[j]++;
                } else {
                    if (giftAmount[i] > giftAmount[j]) {
                        presentCount[i]++;
                    } else if (giftAmount[i] < giftAmount[j]) {
                        presentCount[j]++;
                    }
                }
            }
        }

        Arrays.sort(presentCount);
        return presentCount[presentCount.length - 1];
    }

}