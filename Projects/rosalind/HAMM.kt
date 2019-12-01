package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.IntStream

object HAMM {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.next()
        val pat = stdin.next()
        println(IntStream.range(0, seq.length).filter { i: Int -> seq[i] != pat[i] }.count())
        stdin.close()
    }
}