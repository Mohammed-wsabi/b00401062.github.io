import java.io.*
import java.util.*

object RNA {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val seq = stdin.nextLine()
        System.out.print(seq.replaceAll("T", "U"))
        stdin.close()
    }
}
