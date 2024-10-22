package leetcode;

import java.util.*;
import java.util.stream.Collectors;

class LeetCode58 {
    public int lengthOfLastWord(String s) {
        List<String> splits = Arrays.stream(s.split(" "))
                .map(String::trim)
                .collect(Collectors.toList());

        return splits.get(splits.size() - 1).length();
    }
}
