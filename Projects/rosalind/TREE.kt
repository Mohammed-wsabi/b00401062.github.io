package rosalind

import java.io.IOException
import java.util.*

object TREE {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var n = stdin.nextLine().toInt()
        while (stdin.hasNextLine()) {
            stdin.nextLine()
            n--
        }
        println(n)
        stdin.close()
    }
}