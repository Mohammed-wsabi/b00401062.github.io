package leetcode;

class MaxResult {
    public static int maxResult(int[] nums, int k) {
        final int n = nums.length;
        final int[] cache = new int[n];
        cache[0] = nums[0];
        for (int i = 1; i < n; i++) {
            cache[i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < n; i++) {
            final int num = nums[i];
            for (int j = 1; j <= Math.min(k, i); j++) {
                cache[i] = Math.max(cache[i], num + cache[i - j]);
            }
        }
        return cache[n - 1];
    }
}
