package rosalind

import java.io.IOException
import java.util.*

object MRNA {
    private val TABLE = intArrayOf(4, 0, 2, 2, 2, 2, 4, 2, 3, 0, 2, 6, 1, 2, 0, 4, 2, 6, 6, 4, 0, 4, 1, 0, 2, 0)
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        val seq = stdin.next()
        var count = 3
        for (i in 0 until seq.length) count = count * TABLE[seq[i] - 'A'] % 1000000
        stdout.println(count)
        stdin.close()
    }
}