/*
ID: rombin82
LANG: JAVA
TASK: sprime
*/

import java.io.*
import java.util.*

object sprime {
    private var stdout: PrintWriter? = null
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("sprime.in"))
        stdout = PrintWriter(File("sprime.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout!!.flush() })
        val n = stdin.nextInt()
        for (i in intArrayOf(2, 3, 5, 7))
            solve(i, n)
    }

    private fun solve(current: Int, n: Int) {
        if (!isPrime(current))
            return
        val current_len = if (current == 0) 0 else String.valueOf(current).length()
        if (current_len == n)
            stdout!!.println(current)
        else
            for (i in intArrayOf(1, 3, 5, 7, 9))
                solve(10 * current + i, n)
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 2)
            return true
        if (n == 1 || n % 2 == 0)
            return false
        var i = 3
        while (i <= Math.sqrt(n)) {
            if (n % i == 0)
                return false
            i += 2
        }
        return true
    }
}
