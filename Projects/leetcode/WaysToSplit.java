package leetcode;

public class WaysToSplit {
    private static final int MODULO = 1000000007;

    public static int waysToSplit(int[] nums) {
        final int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; ++i)
            nums[i] += nums[i - 1];
        for (int i = 0, minJ = 0, maxJ = 0; i < n - 2; ++i) {
            while (minJ <= i || (minJ < n - 1 && nums[minJ] < nums[i] * 2))
                ++minJ;
            while (maxJ < minJ || (maxJ < n - 1 && nums[maxJ] - nums[i] <= nums[n - 1] - nums[maxJ]))
                ++maxJ;
            count = (count + maxJ - minJ) % MODULO;
        }
        return count;
    }
}
