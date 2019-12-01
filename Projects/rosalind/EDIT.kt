package rosalind

import java.io.IOException
import java.util.*

object EDIT {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads: MutableList<String?> = ArrayList()
        var read: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                reads.add(read)
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (read != null) reads.add(read)
                read = ""
            } else read += line
        }
        assert(reads.size == 2)
        val s = reads[0]
        val t = reads[1]
        val dp = Array(s!!.length + 1) { IntArray(t!!.length + 1) }
        for (i in 1..s.length) dp[i][0] = i
        for (j in 1..t!!.length) dp[0][j] = j
        for (i in 1..s.length) for (j in 1..t.length) {
            val dirs = intArrayOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + if (s[i - 1] == t[j - 1]) 0 else 1
            )
            dp[i][j] = Arrays.stream(dirs).min().asInt
        }
        println(dp[s.length][t.length])
        stdin.close()
    }
}