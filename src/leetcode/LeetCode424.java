package leetcode;

class LeetCode424 {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];

        int answer = 0;
        int max = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            int currentCount = ++count[s.charAt(r) - 'A'];
            max = Math.max(currentCount, max);

            if ((r - l + 1) - max <= k) {
                answer = Math.max(r - l + 1, answer);
            } else {
                count[s.charAt(l++) - 'A']--;
            }
        }
        return answer;
    }
}
