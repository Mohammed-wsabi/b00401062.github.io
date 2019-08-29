import java.io.*
import java.util.*

object TREE {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var n = Integer.parseInt(stdin.nextLine())
        while (stdin.hasNextLine()) {
            stdin.nextLine()
            n--
        }
        System.out.println(n)
        stdin.close()
    }
}
