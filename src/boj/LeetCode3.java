package boj;

import java.util.*;

class LeetCode3 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> counts = new HashMap<>();

        int l = 0;
        int r = 0;
        int maxLength = 0;

        while (r < s.length()) {
            char current = s.charAt(r);

            if (counts.containsKey(current) && counts.get(current) >= l) {
                int index = counts.get(current);
                l = index + 1;
                counts.remove(current);
            }
            counts.put(current, r);
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }

        return maxLength;
    }
}
