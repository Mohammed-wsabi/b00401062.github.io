import java.io.*
import java.util.*

internal object CHNUM {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var t = stdin.nextInt()
        while (t-- > 0) {
            val n = stdin.nextInt()
            val c = IntArray(3)
            for (i in 0 until n)
                c[Math.signum(stdin.nextInt()) as Int + 1]++
            System.out.printf("%d %d\n", Math.max(c[0], c[2]) + c[1], Arrays.stream(c).filter({ a -> a > 0 }).min().getAsInt())
        }
    }
}
