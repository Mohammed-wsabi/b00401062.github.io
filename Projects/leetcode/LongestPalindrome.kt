class Solution {
    private fun check(s: String, _l: Int, _r: Int): String {
        var l = _l
        var r = _r
        while (l >= 0 && r < s.length) {
            if (s[l] != s[r]) break
            l--
            r++
        }
        return s.subSequence(l+1, r).toString()
    }

    fun longestPalindrome(s: String): String {
        var palindrome: String = ""
        for (i in 0 until s.length) {
            var candidates = listOf(
                    palindrome,
                    check(s, i-1, i+1),
                    check(s, i, i+1)
            )
            palindrome = candidates.maxBy(String::length)!!
        }
        return palindrome
    }
}
