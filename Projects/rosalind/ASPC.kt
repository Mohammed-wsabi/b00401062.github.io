package rosalind

import java.io.IOException
import java.math.BigInteger
import java.util.*

object ASPC {
    private fun choose(n: Int, k: Int): BigInteger {
        var c = BigInteger.ONE
        for (i in n downTo n - k + 1) {
            c = c.multiply(BigInteger(i.toString()))
        }
        for (i in k downTo 1) {
            c = c.divide(BigInteger(i.toString()))
        }
        return c
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val k = stdin.nextInt()
        var sum = BigInteger.ZERO
        for (i in k..n) {
            sum = sum.add(choose(n, i))
        }
        println(sum.mod(BigInteger("1000000")))
        stdin.close()
    }
}