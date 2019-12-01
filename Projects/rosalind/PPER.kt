package rosalind

import java.io.IOException
import java.util.*

object PPER {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val k = stdin.nextInt()
        var ans = 1
        for (i in n downTo n - k + 1) ans = ans * i % 1000000
        println(ans)
        stdin.close()
    }
}