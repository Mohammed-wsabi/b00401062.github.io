internal class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var length = 0
        var substring = ""
        for (i in 0 until s.length()) {
            val c = s.charAt(i)
            substring = substring.substring(substring.indexOf(c) + 1) + c
            length = Math.max(length, substring.length())
        }
        return length
    }
}
