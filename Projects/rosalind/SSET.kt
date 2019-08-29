import java.io.*
import java.util.*
import java.math.*

object SSET {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        System.out.println(BigInteger("2").pow(stdin.nextInt()).mod(BigInteger("1000000")))
    }
}
