package rosalind

import java.io.IOException
import java.util.*

object RNA {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.nextLine()
        print(seq.replace("T".toRegex(), "U"))
        stdin.close()
    }
}