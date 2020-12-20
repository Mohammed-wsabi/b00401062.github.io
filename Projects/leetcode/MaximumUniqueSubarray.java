package leetcode;

import java.util.HashSet;

class MaximumUniqueSubarray {
    public static int maximumUniqueSubarray(int[] nums) {
        int maxScore = 0;
        int curScore = 0;
        int head = 0;
        Set<Integer> set = new HashSet<>();
        for (int tail = 0; tail < nums.length; tail++) {
            final int num = nums[tail];
            if (set.contains(num)) {
                maxScore = Math.max(maxScore, curScore);
                while (nums[start] != num) {
                    int removedNum = nums[start++];
                    set.remove(removedNum);
                    curScore -= removedNum;
                }
                start++;
            } else {
                set.add(num);
                curScore += num;
            }
        }
        maxScore = Math.max(maxScore, curScore);
        return maxScore;
    }
}
