package leetcode

import kotlin.math.min

fun jump(nums: IntArray): Int {
    if (nums.size == 1) return 0
    val jumps = IntArray(nums.size) { Int.MAX_VALUE }
    jumps[0] = 0
    for (lt in 1 until nums.size) {
        for (rt in 0 until lt) {
            val num = nums[rt]
            if (rt + num < lt) continue
            jumps[lt] = min(jumps[lt], jumps[rt] + 1)
        }
    }
    return jumps.last()
}
