package rosalind

import java.io.IOException
import java.util.*

object LCSQ {
    private fun lcs(s: String, t: String): String {
        val table = Array(s.length + 1) { IntArray(t.length + 1) }
        for (i in 1..s.length) for (j in 1..t.length) table[i][j] = if (s[i - 1] == t[j - 1]) table[i - 1][j - 1] + 1 else Math.max(table[i - 1][j], table[i][j - 1])
        val lcs = StringBuilder()
        var i = s.length
        var j = t.length
        while (i > 0 && j > 0) {
            if (s[i - 1] == t[j - 1]) {
                lcs.append(s[i - 1])
                i--
                j--
            } else if (table[i][j] == table[i - 1][j]) i-- else if (table[i][j] == table[i][j - 1]) j--
        }
        return lcs.reverse().toString()
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var s = ""
        var t = ""
        var line = stdin.next()
        while (stdin.hasNext()) {
            if (stdin.next().also { line = it }[0] == '>') break
            s += line
        }
        while (stdin.hasNext()) t += stdin.next()
        println(lcs(s, t))
        stdin.close()
    }
}