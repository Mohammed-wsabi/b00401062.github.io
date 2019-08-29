import java.io.*
import java.util.*
import java.math.*

object MMCH {
    private val A = 'A'.toInt()
    private val C = 'C'.toInt()
    private val G = 'G'.toInt()
    private val U = 'U'.toInt()
    private fun permute(N: Int, r: Int): BigInteger {
        var x = BigInteger.ONE
        for (i in N downTo N - r + 1)
            x = x.multiply(BigInteger.valueOf(i))
        return x
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var read = ""
        while (stdin.hasNext())
            read += stdin.next()
        val a = read.chars().filter(???({ A.equals() })).count() as Int
        val c = read.chars().filter(???({ C.equals() })).count() as Int
        val g = read.chars().filter(???({ G.equals() })).count() as Int
        val u = read.chars().filter(???({ U.equals() })).count() as Int
        System.out.println(permute(Math.max(a, u), Math.min(a, u)).multiply(permute(Math.max(c, g), Math.min(c, g))))
    }
}
