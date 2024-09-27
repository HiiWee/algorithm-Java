package leetcode;

class LeetCode129 {
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

    int totalSum = 0;

    public int sumNumbers(TreeNode root) {

        search(root, root.val);

        return totalSum;
    }

    public void search(TreeNode node, int sum) {
        if (node.left == null && node.right == null) {
            totalSum += sum;
        }

        if (node.left != null) {
            search(node.left, sum * 10 + node.left.val);
        }
        if (node.right != null) {
            search(node.right, sum * 10 + node.right.val);
        }
    }
}
