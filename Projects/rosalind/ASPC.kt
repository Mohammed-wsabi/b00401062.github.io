import java.io.*
import java.util.*
import java.math.*

object ASPC {
    private fun choose(n: Int, k: Int): BigInteger {
        var c = BigInteger.ONE
        for (i in n downTo n - k + 1)
            c = c.multiply(BigInteger(String.valueOf(i)))
        for (i in k downTo 1)
            c = c.divide(BigInteger(String.valueOf(i)))
        return c
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val k = stdin.nextInt()
        var sum = BigInteger.ZERO
        for (i in k..n)
            sum = sum.add(choose(n, i))
        System.out.println(sum.mod(BigInteger("1000000")))
    }
}
