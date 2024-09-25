package leetcode;

import java.util.*;

class LeetCode39 {

    int target;
    List<List<Integer>> answers = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;

        search(0, 0, candidates, new ArrayList<>());

        return answers;
    }

    public void search(int index, int sum, int[] candidates, List<Integer> answer) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            answers.add(new ArrayList<>(answer));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            answer.add(candidates[i]);
            search(i, sum + candidates[i], candidates, answer);
            answer.remove(Integer.valueOf(candidates[i]));
        }
    }
}
