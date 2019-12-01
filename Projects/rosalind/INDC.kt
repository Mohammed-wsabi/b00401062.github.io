package rosalind

import java.io.IOException
import java.util.*

object INDC {
    private fun choose(N: Int, k: Int): Double {
        var x = 1.0
        for (i in N downTo N - k + 1) x *= i.toDouble()
        for (i in k downTo 1) x /= i.toDouble()
        return x
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        for (k in 1..2 * n) {
            var p = 0.0
            for (i in k..2 * n) p += choose(2 * n, i) * Math.pow(0.5, 2 * n.toDouble())
            System.out.printf("%.3f\n", Math.log10(p))
        }
        stdin.close()
    }
}