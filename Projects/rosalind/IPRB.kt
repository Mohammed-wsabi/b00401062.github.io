package rosalind

import java.io.IOException
import java.util.*

object IPRB {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val k = stdin.nextInt()
        val m = stdin.nextInt()
        val n = stdin.nextInt()
        val all = (k + m + n) * (k + m + n - 1) / 2
        val pmm = m.toDouble() * (m - 1) / 8 / all
        val pmn = m.toDouble() * n / 2 / all
        val pnn = n.toDouble() * (n - 1) / 2 / all
        println(1 - pmm - pmn - pnn)
        stdin.close()
    }
}