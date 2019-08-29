import java.io.*
import java.util.*
import java.util.stream.*

object HAMM {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.next()
        val pat = stdin.next()
        System.out.println(IntStream.range(0, seq.length()).filter({ i -> seq.charAt(i) !== pat.charAt(i) }).count())
        stdin.close()
    }
}
