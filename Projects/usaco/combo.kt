/*
ID: rombin82
LANG: JAVA
TASK: combo
*/

import java.io.*
import java.util.*

object combo {
    private var N: Int = 0
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("combo.in"))
        val stdout = PrintWriter(File("combo.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        N = stdin.nextInt()
        val fkey = IntArray(3)
        val mkey = IntArray(3)
        for (i in 0..2)
            fkey[i] = stdin.nextInt()
        for (i in 0..2)
            mkey[i] = stdin.nextInt()
        var count = 0
        for (d1 in 1..N)
            for (d2 in 1..N)
                for (d3 in 1..N) {
                    val combo = intArrayOf(d1, d2, d3)
                    if (isClose(combo, fkey) || isClose(combo, mkey))
                        count++
                }
        stdout.println(count)
    }

    private fun isClose(a: IntArray, b: IntArray): Boolean {
        for (i in 0..2)
            if (Math.abs(a[i] - b[i]) > 2 && Math.abs(a[i] - b[i]) < N - 2)
                return false
        return true
    }
}
