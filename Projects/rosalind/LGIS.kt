package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object LGIS {
    private fun dp(s: IntArray, d: Int): List<Int?> {
        val n = s.size
        val len = IntArray(n)
        val prv = IntArray(n)
        Arrays.fill(len, 1)
        Arrays.fill(prv, -1)
        for (i in 1 until n) for (j in 0 until i) if ((if (s[j] < s[i]) 1 - d else d) == 1 && len[i] < len[j] + 1) {
            len[i] = len[j] + 1
            prv[i] = j
        }
        var m = IntStream.range(0, n).boxed().max(Comparator.comparingInt { i: Int? -> len[i!!] }).get()
        val dp: MutableList<Int?> = ArrayList()
        do {
            dp.add(s[m])
        } while (prv[m].also { m = it } != -1)
        Collections.reverse(dp)
        return dp
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val s = IntArray(n)
        for (i in 0 until n) s[i] = stdin.nextInt()
        println(dp(s, 0).stream().map { obj: Int? -> obj.toString() }.collect(Collectors.joining(" ")))
        println(dp(s, 1).stream().map { obj: Int? -> obj.toString() }.collect(Collectors.joining(" ")))
        stdin.close()
    }
}