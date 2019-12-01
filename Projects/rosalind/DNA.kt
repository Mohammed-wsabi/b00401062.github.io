package rosalind

import java.io.IOException
import java.util.*

object DNA {
    private val DNABASES = charArrayOf('A', 'C', 'G', 'T')
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.nextLine()
        for (base in DNABASES) println(seq.chars().filter { x: Int -> x == base.toInt() }.count())
        stdin.close()
    }
}