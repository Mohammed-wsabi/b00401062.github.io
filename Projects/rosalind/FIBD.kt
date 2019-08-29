import java.io.*
import java.util.*

object FIBD {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val m = stdin.nextInt()
        var mature: Long = 0
        val generation = LongArray(n + m - 1)
        generation[m - 1] = 1
        for (i in m until n + m - 1) {
            generation[i] = mature
            mature = mature - generation[i - m] + generation[i - 1]
        }
        System.out.println(mature + generation[n + m - 2])
        stdin.close()
    }
}
