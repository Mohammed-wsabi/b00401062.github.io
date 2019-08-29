import java.io.*
import java.util.*
import java.math.BigInteger

object PMCH {
    private val A = 'A'.toInt()
    private val G = 'G'.toInt()
    private fun factorial(i: Long): BigInteger {
        return if (i == 0L) BigInteger.ONE else factorial(i - 1).multiply(BigInteger.valueOf(i))
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        stdin.next()
        var seq = ""
        while (stdin.hasNext())
            seq += stdin.next()
        System.out.println(factorial(seq.chars().filter(???{ A.equals() }).count()).multiply(factorial(seq.chars().filter(???({ G.equals() })).count())))
        stdin.close()
    }
}
