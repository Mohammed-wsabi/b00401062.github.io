import java.io.*
import java.util.*

object LIA {
    private val T = arrayOf(doubleArrayOf(4.0 / 16, 2.0 / 16, 0.0 / 16, 2.0 / 16, 1.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16), // AA BB
            doubleArrayOf(4.0 / 16, 4.0 / 16, 4.0 / 16, 2.0 / 16, 2.0 / 16, 2.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16), // AA Bb
            doubleArrayOf(0.0 / 16, 2.0 / 16, 4.0 / 16, 0.0 / 16, 1.0 / 16, 2.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16), // AA bb
            doubleArrayOf(4.0 / 16, 2.0 / 16, 0.0 / 16, 4.0 / 16, 2.0 / 16, 0.0 / 16, 4.0 / 16, 2.0 / 16, 0.0 / 16), // Aa BB
            doubleArrayOf(4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16), // Aa Bb
            doubleArrayOf(0.0 / 16, 2.0 / 16, 4.0 / 16, 0.0 / 16, 2.0 / 16, 4.0 / 16, 0.0 / 16, 2.0 / 16, 4.0 / 16), // Aa bb
            doubleArrayOf(0.0 / 16, 0.0 / 16, 0.0 / 16, 2.0 / 16, 1.0 / 16, 0.0 / 16, 4.0 / 16, 2.0 / 16, 0.0 / 16), // aa BB
            doubleArrayOf(0.0 / 16, 0.0 / 16, 0.0 / 16, 2.0 / 16, 2.0 / 16, 2.0 / 16, 4.0 / 16, 4.0 / 16, 4.0 / 16), // aa Bb
            doubleArrayOf(0.0 / 16, 0.0 / 16, 0.0 / 16, 0.0 / 16, 1.0 / 16, 2.0 / 16, 0.0 / 16, 2.0 / 16, 4.0 / 16))// aa bb

    private fun choose(N: Int, r: Int): Double {
        var x = 1.0
        for (i in N downTo N - r + 1)
            x *= i.toDouble()
        for (i in r downTo 1)
            x /= i.toDouble()
        return x
    }

    private fun multiply(A: Array<DoubleArray>, x: DoubleArray): DoubleArray {
        val b = DoubleArray(x.size)
        for (i in A.indices)
            for (j in 0 until A[0].size)
                b[i] += A[i][j] * x[j]
        return b
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val k = stdin.nextInt()
        val n = stdin.nextInt()
        var x = doubleArrayOf(0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0)
        for (i in 0 until k)
            x = multiply(T, x)
        val N = Math.pow(2, k) as Int
        var P = 0.0
        val p = x[4]
        for (i in n..N)
            P += choose(N, i) * Math.pow(p, i) * Math.pow(1 - p, N - i)
        System.out.println(P)
        stdin.close()
    }
}
