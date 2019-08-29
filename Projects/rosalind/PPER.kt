import java.io.*
import java.util.*

object PPER {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val n = stdin.nextInt()
        val k = stdin.nextInt()
        var ans = 1
        for (i in n downTo n - k + 1)
            ans = ans * i % 1000000
        System.out.println(ans)
        stdin.close()
    }
}
