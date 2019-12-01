package rosalind

import java.io.IOException
import java.util.*

object EVAL {
    private const val A = 'A'.toInt()
    private const val C = 'C'.toInt()
    private const val G = 'G'.toInt()
    private const val T = 'T'.toInt()
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val read = stdin.next()
        val k = read.length
        val a = read.chars().filter { obj: Int -> A.equals(obj) }.count().toInt()
        val c = read.chars().filter { obj: Int -> C.equals(obj) }.count().toInt()
        val g = read.chars().filter { obj: Int -> G.equals(obj) }.count().toInt()
        val t = read.chars().filter { obj: Int -> T.equals(obj) }.count().toInt()
        while (stdin.hasNext()) {
            val p = stdin.nextDouble()
            println((n - k + 1) * Math.pow(p / 2, c + g.toDouble()) * Math.pow((1 - p) / 2, a + t.toDouble()))
        }
        stdin.close()
    }
}