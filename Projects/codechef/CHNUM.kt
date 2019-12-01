package codechef

import java.io.IOException
import java.util.*

internal object CHNUM {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var t = stdin.nextInt()
        while (t-- > 0) {
            val n = stdin.nextInt()
            val c = IntArray(3)
            for (i in 0 until n) c[Math.signum(stdin.nextInt().toFloat()).toInt() + 1]++
            System.out.printf("%d %d\n", Math.max(c[0], c[2]) + c[1], Arrays.stream(c).filter { a: Int -> a > 0 }.min().asInt)
        }
        stdin.close()
    }
}