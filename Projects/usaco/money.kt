/*
ID: rombin82
LANG: JAVA
TASK: money
*/

import java.io.*
import java.util.*

object money {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("money.in"))
        val stdout = PrintWriter(File("money.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val V = stdin.nextInt()
        val N = stdin.nextInt()
        val coins = IntArray(V)
        for (v in 0 until V)
            coins[v] = stdin.nextInt()
        stdin.close()
        val counts = IntArray(N + 1)
        counts[0] = 1
        for (v in 0 until V)
            for (n in coins[v]..N)
                counts[n] += counts[n - coins[v]]
        stdout.println(counts[N])
    }
}
