package rosalind

import java.io.IOException
import java.util.*

object IEV {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var ans = 0.0
        ans += 2 * (stdin.nextInt() + stdin.nextInt() + stdin.nextInt()).toDouble()
        ans += 1.5 * stdin.nextInt() + stdin.nextInt()
        println(ans)
        stdin.close()
    }
}
