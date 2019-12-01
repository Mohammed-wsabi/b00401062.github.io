package rosalind

import java.io.IOException
import java.util.*

object MOTZ {
    private val CACHE: MutableMap<String, Int?> = mutableMapOf(
        "" to 1,
        "A" to 1, "C" to 1, "G" to 1, "U" to 1,
        "AA" to 1, "AC" to 1, "AG" to 1, "AU" to 2,
        "CA" to 1, "CC" to 1, "CG" to 2, "CU" to 1,
        "GA" to 1, "GC" to 2, "GG" to 1, "GU" to 1,
        "UA" to 2, "UC" to 1, "UG" to 1, "UU" to 1
    )

    private fun motzkin(read: String): Int {
        if (CACHE.containsKey(read)) return CACHE[read]!!
        var motzkin = motzkin(read.substring(1))
        for (i in 1 until read.length) {
            if (CACHE[String.format("%c%c", read[0], read[i])] == 1) continue
            val head = read.substring(1, i)
            val tail = read.substring(i + 1)
            if (!CACHE.containsKey(head)) CACHE[head] = motzkin(head)
            if (!CACHE.containsKey(tail)) CACHE[tail] = motzkin(tail)
            motzkin = ((CACHE[head] as Long * CACHE[tail]!! + motzkin) % 1000000).toInt()
        }
        return motzkin
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        stdin.next()
        var read = ""
        while (stdin.hasNext()) read += stdin.next()
        println(motzkin(read))
        stdin.close()
    }
}