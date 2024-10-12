package leetcode;

class LeetCode242 {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alphaCount = new int[26];

        for (char letter : s.toCharArray()) {
            alphaCount[letter - 'a']++;
        }

        for (char letter : t.toCharArray()) {
            alphaCount[letter - 'a']--;

            if (alphaCount[letter - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
