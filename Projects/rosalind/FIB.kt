import java.io.*
import java.util.*

object FIB {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val k = stdin.nextInt()
        var mature: Long = 0
        val generation = LongArray(n)
        generation[0] = 1
        for (i in 1 until n) {
            generation[i] = mature * k
            mature += generation[i - 1]
        }
        System.out.println(mature + generation[n - 1])
        stdin.close()
    }
}
