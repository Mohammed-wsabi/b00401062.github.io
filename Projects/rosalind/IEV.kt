import java.io.*
import java.util.*

object IEV {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var ans = 0.0
        ans += 2 * (stdin.nextInt() + stdin.nextInt() + stdin.nextInt())
        ans += 1.5 * stdin.nextInt() + stdin.nextInt()
        System.out.println(ans)
        stdin.close()
    }
}
