package leetcode;

import java.util.*;

class LeetCode199 {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        search(0, answer, root);

        return answer;
    }

    public void search(int level, List<Integer> values, TreeNode node) {
        if (node == null) {
            return;
        }
        if (values.size() == level) {
            values.add(node.val);
        }

        search(level + 1, values, node.right);
        search(level + 1, values, node.left);
    }
}
