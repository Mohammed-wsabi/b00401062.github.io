import java.io.*
import java.util.*

object EVAL {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val read = stdin.next()
        val k = read.length()
        val a = read.chars().filter(???({ A.equals() })).count() as Int
        val c = read.chars().filter(???({ C.equals() })).count() as Int
        val g = read.chars().filter(???({ G.equals() })).count() as Int
        val t = read.chars().filter(???({ T.equals() })).count() as Int
        while (stdin.hasNext()) {
            val p = stdin.nextDouble()
            System.out.println((n - k + 1) * Math.pow(p / 2, c + g) * Math.pow((1 - p) / 2, a + t))
        }
    }
}
