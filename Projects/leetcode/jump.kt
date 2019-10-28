package leetcode

import kotlin.math.min

fun jump(nums: IntArray): Int {
    if (nums.size == 1) return 0
    val jumps = IntArray(nums.size) { Int.MAX_VALUE }
    jumps[0] = 0
    for (rt in 1 until nums.size) {
        for (lt in 0 until rt) {
            val num = nums[lt]
            if (lt + num < rt) continue
            jumps[rt] = min(jumps[rt], jumps[lt] + 1)
        }
    }
    return jumps.last()
}
