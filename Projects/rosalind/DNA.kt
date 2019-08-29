import java.io.*
import java.util.*

object DNA {
    private val DNABASES = charArrayOf('A', 'C', 'G', 'T')
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.nextLine()
        for (base in DNABASES)
            System.out.println(seq.chars().filter({ x -> x === base }).count())
        stdin.close()
    }
}
