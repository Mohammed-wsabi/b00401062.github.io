package rosalind

import java.io.IOException
import java.util.*

object INOD {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        println(stdin.nextInt() - 2)
        stdin.close()
    }
}