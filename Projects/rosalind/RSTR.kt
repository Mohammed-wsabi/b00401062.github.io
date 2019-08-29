import java.io.*
import java.util.*

object RSTR {
    private val A = 'A'.toInt()
    private val C = 'C'.toInt()
    private val G = 'G'.toInt()
    private val T = 'T'.toInt()
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val p = stdin.nextDouble()
        val read = stdin.next()
        val a = read.chars().filter(???({ A.equals() })).count() as Int
        val c = read.chars().filter(???({ C.equals() })).count() as Int
        val g = read.chars().filter(???({ G.equals() })).count() as Int
        val t = read.chars().filter(???({ T.equals() })).count() as Int
        System.out.println(1 - Math.pow(1 - Math.pow(p / 2, c + g) * Math.pow((1 - p) / 2, a + t), n))
    }
}
