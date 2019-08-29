import java.io.*
import java.util.*

object INOD {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        System.out.println(stdin.nextInt() - 2)
    }
}
