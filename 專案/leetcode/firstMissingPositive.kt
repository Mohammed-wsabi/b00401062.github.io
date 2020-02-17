package leetcode

import kotlin.math.abs

fun firstMissingPositive(nums: IntArray): Int {
    nums.withIndex().forEach {(index, num) ->
        if (num <= 0)
            nums[index] = Int.MAX_VALUE
    }
    nums.forEach {
        val pos = abs(it) - 1
        if (pos >= nums.size)
            return@forEach
        if (nums[pos] > 0)
            nums[pos] = -nums[pos]
    }
    nums.withIndex().forEach {(index, num) ->
        if (num > 0)
            return index + 1
    }
    return nums.size + 1
}
