/*
ID: rombin82
LANG: JAVA
TASK: nocows
*/

import java.io.*
import java.util.*

object nocows {
    fun evaluate(N: Int, K: Int): Int {
        if (N % 2 == 0 || N >= Math.pow(2, K) as Int)
            return 0
        val table = Array(N + 1) { IntArray(K + 1) }
        table[1][1] = 1
        var n = 3
        while (n <= N) {
            for (k in 1..K) {
                var i = 1
                while (i <= (n - 2) / 2) {
                    val sum1 = Arrays.stream(Arrays.copyOfRange(table[n - i - 1], 0, k)).sum() % 9901
                    val sum2 = Arrays.stream(Arrays.copyOfRange(table[i], 0, k)).sum() % 9901
                    table[n][k] += (sum1 * sum2 - (sum1 - table[n - i - 1][k - 1]) * (sum2 - table[i][k - 1])) * 2
                    i += 2
                }
                if (n % 4 == 3) {
                    val sum = Arrays.stream(Arrays.copyOfRange(table[n / 2], 0, k)).sum() % 9901
                    table[n][k] += sum * sum - (sum - table[n / 2][k - 1]) * (sum - table[n / 2][k - 1])
                }
                table[n][k] %= 9901
            }
            n += 2
        }
        return table[N][K] % 9901
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("nocows.in"))
        val stdout = PrintWriter(File("nocows.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        val K = stdin.nextInt()
        stdin.close()
        stdout.println(evaluate(N, K))
    }
}
