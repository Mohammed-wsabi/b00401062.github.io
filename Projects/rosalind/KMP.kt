package rosalind

import java.io.IOException
import java.util.*

object KMP {
    private fun preprocess(t: String): IntArray {
        val table = IntArray(t.length)
        table[0] = -1
        var k = -1
        for (i in 1 until t.length) {
            while (k >= 0 && t[k + 1] != t[i]) k = table[k]
            if (t[k + 1] == t[i]) k++
            table[i] = k
        }
        return table
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val read = StringBuilder()
        stdin.next()
        while (stdin.hasNext()) read.append(stdin.next())
        Arrays.stream(preprocess(read.toString())).map { x: Int -> x + 1 }.forEach { x: Int -> println(x) }
        stdin.close()
    }
}