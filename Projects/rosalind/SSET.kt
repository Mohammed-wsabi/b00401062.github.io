package rosalind

import java.io.IOException
import java.math.BigInteger
import java.util.*

object SSET {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        println(BigInteger("2").pow(stdin.nextInt()).mod(BigInteger("1000000")))
        stdin.close()
    }
}