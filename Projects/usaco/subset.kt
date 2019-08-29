/*
ID: rombin82
LANG: JAVA
TASK: subset
*/

import java.io.*
import java.util.*

object subset {
    private var N: Int = 0
    private fun countSubset(sum: Int): Long {
        val dict = Array(N + 1) { LongArray(sum + 1) }
        for (i in 0..N)
            dict[i][0] = 1
        for (i in 1..N)
            for (j in 1..sum)
                dict[i][j] = dict[i - 1][j] + if (j - i < 0) 0 else dict[i - 1][j - i]
        return dict[N][sum]
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("subset.in"))
        val stdout = PrintWriter(File("subset.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        N = stdin.nextInt()
        val sum = N * (N + 1) / 2
        stdout.println(if (sum % 2 == 0) countSubset(sum / 2) / 2 else 0)
    }
}
