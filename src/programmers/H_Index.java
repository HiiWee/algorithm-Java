package programmers;

import java.util.*;

class H_Index {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (h <= citations[i]) {
                answer = h;
                break;
            }
        }
        return answer;
    }
}