package rosalind

import java.io.IOException
import java.math.BigInteger
import java.util.*

object PMCH {
    private const val A = 'A'.toInt()
    private const val G = 'G'.toInt()
    private fun factorial(i: Long): BigInteger {
        return if (i == 0L) BigInteger.ONE else factorial(i - 1).multiply(BigInteger.valueOf(i))
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        stdin.next()
        var seq = ""
        while (stdin.hasNext()) seq += stdin.next()
        println(factorial(seq.chars().filter { obj: Int -> A.equals(obj) }.count()).multiply(factorial(seq.chars().filter { obj: Int -> G.equals(obj) }.count())))
        stdin.close()
    }
}