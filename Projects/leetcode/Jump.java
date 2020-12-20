package leetcode;

class Jump {
    public int jump(int[] nums) {
        final int n = nums.length;
        if (n == 1) return 0;
        int curJump = 0;
        int nextMaxReach = 0;
        int currMaxReach = 0;
        for (int i = 0; i < n; i++) {
            nextMaxReach = Math.max(nextMaxReach, i + nums[i]);
            if (nextMaxReach >= n - 1) break;
            if (i == currMaxReach) {
                curJump++;
                currMaxReach = nextMaxReach;
            }
        }
        return curJump + 1;
    }
}
