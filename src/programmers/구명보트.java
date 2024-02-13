package programmers;

import java.util.*;

class 구명보트 {
    int n;
    public int solution(int[] people, int limit) {
        n = people.length;

        Arrays.sort(people);
        int l = 0;
        int r = n - 1;
        int count = 0;
        while (l < r) {
            // 무거운 사람과 작은 사람이 합승가능 할 경우
            if (people[l] + people[r] <= limit) {
                l++;
                r--;
                count++;
            } else {
                // 불가능하면 무거운사람 내보냄
                r--;
                count++;
            }
        }
        // 같이 합승할 수 없는 경우 r값만 줄어듦, 따라서 l < r이 false가 되는 경계점의 값까지 r이 전진한 횟수가 결국 보트를 사용한 횟수가 됩니다. 해당 값은 n - l의 값과 일치하게 됩니다.
        return n - l;
    }
}
