package leetcode;

import java.util.*;

class LeetCode49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] splits = strs[i].toCharArray();
            Arrays.sort(splits);
            anagrams.computeIfAbsent(String.valueOf(splits), (key) -> new ArrayList<>())
                    .add(strs[i]);
        }

        return new ArrayList<>(anagrams.values());
    }
}
