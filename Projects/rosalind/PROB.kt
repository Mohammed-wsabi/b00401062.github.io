package rosalind

import java.io.IOException
import java.util.*

object PROB {
    private const val A = 'A'.toInt()
    private const val C = 'C'.toInt()
    private const val G = 'G'.toInt()
    private const val T = 'T'.toInt()
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.next()
        val a = seq.chars().filter { obj: Int -> A.equals(obj) }.count().toInt()
        val c = seq.chars().filter { obj: Int -> C.equals(obj) }.count().toInt()
        val g = seq.chars().filter { obj: Int -> G.equals(obj) }.count().toInt()
        val t = seq.chars().filter { obj: Int -> T.equals(obj) }.count().toInt()
        while (stdin.hasNext()) {
            val p = stdin.nextDouble()
            println(Math.log10(p / 2) * (c + g) + Math.log10((1 - p) / 2) * (a + t))
        }
        stdin.close()
    }
}