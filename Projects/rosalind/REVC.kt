package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors

object REVC {
    private val COMPLEMENT: Map<Char, Char> = mapOf(
        'A' to 'T', 'C' to 'G', 'G' to 'C', 'T' to 'A'
    )

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.nextLine()
        println(seq.reversed().map { COMPLEMENT[it] }.joinToString(""))
        stdin.close()
    }
}