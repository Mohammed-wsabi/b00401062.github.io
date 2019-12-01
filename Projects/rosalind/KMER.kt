package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object KMER {
    private fun str2int(str: String): Int {
        return IntStream.range(0, 4).map { x: Int -> Math.pow(4.0, 3 - x.toDouble()).toInt() * "ACGT".indexOf(str[x]) }.sum()
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var read = ""
        val counts = IntArray(256)
        stdin.next()
        while (stdin.hasNext()) {
            read += stdin.next()
            for (i in 0 until read.length - 3) counts[str2int(read.substring(i, i + 4))]++
            read = read.substring(read.length - 3)
        }
        println(Arrays.stream(counts).mapToObj { i: Int -> i.toString() }.collect(Collectors.joining(" ")))
        stdin.close()
    }
}