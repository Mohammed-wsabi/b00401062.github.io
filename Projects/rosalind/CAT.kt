package rosalind

import java.io.IOException
import java.util.*

object CAT {
    private val CACHE: MutableMap<String, Long> = mutableMapOf(
        "" to 1L,
        "AA" to 0L, "AC" to 0L, "AG" to 0L, "AU" to 1L,
        "CA" to 0L, "CC" to 0L, "CG" to 1L, "CU" to 0L,
        "GA" to 0L, "GC" to 1L, "GG" to 0L, "GU" to 0L,
        "UA" to 1L, "UC" to 0L, "UG" to 0L, "UU" to 0L
    )

    private fun catalan(read: String): Long {
        var catalan = 0L
        if (CACHE.containsKey(read)) return CACHE[read]!!
        for (i in 0 until read.length / 2) {
            val dimer = String.format("%c%c", read[0], read[2 * i + 1])
            if (CACHE[dimer] == 0L) continue
            val head = read.substring(1, 2 * i + 1)
            val tail = read.substring(2 * i + 2)
            if (!CACHE.containsKey(head)) CACHE[head] = catalan(head)
            if (!CACHE.containsKey(tail)) CACHE[tail] = catalan(tail)
            catalan = (catalan + CACHE[head]!! * CACHE[tail]!!) % 1000000
        }
        return catalan
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        stdin.next()
        var read = ""
        while (stdin.hasNext()) read += stdin.next()
        println(catalan(read))
        stdin.close()
    }
}