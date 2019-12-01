package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object PDST {
    private fun hamming(s: String?, t: String?): Long {
        val l = s!!.length
        return IntStream.range(0, l).filter { i: Int -> s[i] != t!![i] }.count()
    }

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
        val n = reads.size
        val l = reads[0]!!.length
        val d = Array(n) { DoubleArray(n) }
        for (i in 0 until n) for (j in i + 1 until n) {
            d[j][i] = hamming(reads[i], reads[j]).toDouble() / l
            d[i][j] = d[j][i]
        }
        for (i in 0 until n) println(Arrays.stream(d[i]).mapToObj { d: Double -> d.toString() }.collect(Collectors.joining(" ")))
        stdin.close()
    }
}