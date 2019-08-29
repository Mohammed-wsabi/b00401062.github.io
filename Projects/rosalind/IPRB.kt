import java.io.*
import java.util.*

object IPRB {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val k = stdin.nextInt()
        val m = stdin.nextInt()
        val n = stdin.nextInt()
        val all = (k + m + n) * (k + m + n - 1) / 2
        val pmm = m.toDouble() * (m - 1) / 8.0 / all.toDouble()
        val pmn = m.toDouble() * n / 2.0 / all.toDouble()
        val pnn = n.toDouble() * (n - 1) / 2.0 / all.toDouble()
        System.out.println(1.0 - pmm - pmn - pnn)
        stdin.close()
    }
}
