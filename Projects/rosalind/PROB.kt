import java.io.*
import java.util.*

object PROB {
    private val A = 'A'.toInt()
    private val C = 'C'.toInt()
    private val G = 'G'.toInt()
    private val T = 'T'.toInt()
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.next()
        val a = seq.chars().filter(???({ A.equals() })).count() as Int
        val c = seq.chars().filter(???({ C.equals() })).count() as Int
        val g = seq.chars().filter(???({ G.equals() })).count() as Int
        val t = seq.chars().filter(???({ T.equals() })).count() as Int
        while (stdin.hasNext()) {
            val p = stdin.nextDouble()
            System.out.println(Math.log10(p / 2) * (c + g) + Math.log10((1 - p) / 2) * (a + t))
        }
        stdin.close()
    }
}
