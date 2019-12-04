package leetcode

import kotlin.math.max

fun maxSubArray(nums: IntArray): Int {
    var sum: Int = 0
    var maxSum: Int = Int.MIN_VALUE
    for (num in nums) {
        sum = if (sum >= 0) sum + num else num
        maxSum = max(sum, maxSum)
    }
    return maxSum
}