package leetcode

fun jump(nums: IntArray): Int {
    if (nums.size == 1) return 0
    var jump = 0
    var curr = 0
    var next = 0
    for ((idx, num) in nums.withIndex()) {
        if (idx + num >= nums.lastIndex) {
            return jump + 1
        }
        if (idx + num > next) {
            next = idx + num
        }
        if (idx == curr) {
            curr = next
            jump += 1
        }
    }
    return jump
}
