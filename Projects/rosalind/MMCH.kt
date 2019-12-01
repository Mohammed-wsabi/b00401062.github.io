package rosalind

import java.io.IOException
import java.math.BigInteger
import java.util.*

object MMCH {
    private const val A = 'A'.toInt()
    private const val C = 'C'.toInt()
    private const val G = 'G'.toInt()
    private const val U = 'U'.toInt()
    private fun permute(N: Int, r: Int): BigInteger {
        var x = BigInteger.ONE
        for (i in N downTo N - r + 1) x = x.multiply(BigInteger.valueOf(i.toLong()))
        return x
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var read = ""
        while (stdin.hasNext()) read += stdin.next()
        val a = read.chars().filter { obj: Int -> A.equals(obj) }.count().toInt()
        val c = read.chars().filter { obj: Int -> C.equals(obj) }.count().toInt()
        val g = read.chars().filter { obj: Int -> G.equals(obj) }.count().toInt()
        val u = read.chars().filter { obj: Int -> U.equals(obj) }.count().toInt()
        println(permute(Math.max(a, u), Math.min(a, u)).multiply(permute(Math.max(c, g), Math.min(c, g))))
        stdin.close()
    }
}