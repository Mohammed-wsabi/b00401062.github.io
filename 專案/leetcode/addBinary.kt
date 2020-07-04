package leetcode

import kotlin.math.max

fun addBinary(a: String, b: String): String {
    val res = StringBuilder()
    var carry = 0
    val maxlen = max(a.length, b.length)
    val a = String(CharArray(maxlen - a.length) { '0' }) + a
    val b = String(CharArray(maxlen - b.length) { '0' }) + b
    assert(a.length == b.length)
    for ((ca, cb) in (a zip b).reversed()) {
        val da = ca - '0'
        val db = cb - '0'
        when (da + db + carry) {
            0 -> {
                res.append('0')
                carry = 0
            }
            1 -> {
                res.append('1')
                carry = 0
            }
            2 -> {
                res.append('0')
                carry = 1
            }
            3 -> {
                res.append('1')
                carry = 1
            }
            else -> throw Exception()
        }
    }
    if (carry == 1) {
        res.append('1')
    }
    return res.reverse().toString()
}
