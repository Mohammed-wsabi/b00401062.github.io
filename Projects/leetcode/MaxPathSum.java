package leetcode;

import java.util.Arrays;

class MaxPathSum {
    private int maxPathSum = Integer.MIN_VALUE;

    private static int max(int... args) {
        return Arrays.stream(args).max().orElseThrow();
    }

    private int traverse(TreeNode root) {
        if (root == null) return 0;
        int maxLt = traverse(root.left);
        int maxRt = traverse(root.right);
        maxPathSum = Math.max(maxPathSum, root.val + max(0, maxLt, maxRt, maxLt + maxRt));
        return root.val + max(0, maxLt, maxRt);
    }

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return maxPathSum;
    }
}
