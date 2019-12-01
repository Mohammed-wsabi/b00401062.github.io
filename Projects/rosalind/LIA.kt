package rosalind

import java.io.IOException
import java.util.*
import kotlin.math.pow

object LIA {
    private val T = arrayOf(
        doubleArrayOf(4.0 / 16, 2.0 / 16, 0.0 / 16, 2.0 / 16, 1.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16),
        doubleArrayOf(4.0 / 16, 4.0 / 16, 4.0 / 16, 2.0 / 16, 2.0 / 16, 2.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16),
        doubleArrayOf(0.0 / 16, 2.0 / 16, 4.0 / 16, 0.0 / 16, 1.0 / 16, 2.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16),
        doubleArrayOf(4.0 / 16, 2.0 / 16, 0.0 / 16, 4.0 / 16, 2.0 / 16, 0.0 / 16, 4.0 / 16, 2.0 / 16, 0.0 / 16),
        doubleArrayOf(4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16),
        doubleArrayOf(0.0 / 16, 2.0 / 16, 4.0 / 16, 0.0 / 16, 2.0 / 16, 4.0 / 16, 0.0 / 16, 2.0 / 16, 4.0 / 16),
        doubleArrayOf(0.0 / 16, 0.0 / 16, 0.0 / 16, 2.0 / 16, 1.0 / 16, 0.0 / 16, 4.0 / 16, 2.0 / 16, 0.0 / 16),
        doubleArrayOf(0.0 / 16, 0.0 / 16, 0.0 / 16, 2.0 / 16, 2.0 / 16, 2.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16),
        doubleArrayOf(0.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16, 1.0 / 16, 2.0 / 16, 0.0 / 16, 2.0 / 16, 4.0 / 16)
    )

    private fun choose(N: Int, r: Int): Double {
        var x = 1.0
        for (i in N downTo N - r + 1) x *= i.toDouble()
        for (i in r downTo 1) x /= i.toDouble()
        return x
    }

    private fun multiply(A: Array<DoubleArray>, x: DoubleArray): DoubleArray {
        val b = DoubleArray(x.size)
        for (i in A.indices) {
            for (j in A[0].indices) {
                b[i] += A[i][j] * x[j]
            }
        }
        return b
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val k = stdin.nextInt()
        val n = stdin.nextInt()
        var x = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0)
        for (i in 0 until k) x = multiply(T, x)
        val N = Math.pow(2.0, k.toDouble()).toInt()
        var P = 0.0
        val p = x[4]
        for (i in n..N) {
            P += choose(N, i) * p.pow(i.toDouble()) * (1 - p).pow(N - i.toDouble())
        }
        println(P)
        stdin.close()
    }
}