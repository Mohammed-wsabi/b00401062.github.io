package leetcode;

import java.util.Arrays;

class MaxPathSum {
    private static class Maximum {
        int value = Integer.MIN_VALUE;

        public void compareAndSet(int value) {
            this.value = Math.max(this.value, value);
        }
    }

    private static int max(int... args) {
        return Arrays.stream(args).max().orElseThrow();
    }

    private static int traverse(TreeNode root, Maximum max) {
        if (root == null) return 0;
        int maxLt = traverse(root.left, max);
        int maxRt = traverse(root.right, max);
        max.compareAndSet(root.val + max(0, maxLt, maxRt, maxLt + maxRt));
        return root.val + max(0, maxLt, maxRt);
    }

    public static int maxPathSum(TreeNode root) {
        Maximum max = new Maximum();
        traverse(root, max);
        return max.value;
    }
}
