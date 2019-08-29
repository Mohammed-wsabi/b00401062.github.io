/*
ID: rombin82
LANG: JAVA
TASK: friday
*/

import java.io.*
import java.util.*

object friday {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("friday.in"))
        val stdout = PrintWriter(File("friday.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val counter = IntArray(7)
        val N = stdin.nextInt()
        var dow = 0
        for (year in 1900 until 1900 + N) {
            val dpw = intArrayOf(31, 28 + if (isLeap(year)) 1 else 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
            for (month in 0..11) {
                counter[dow]++
                dow = (dow + dpw[month]) % 7
            }
        }
        for (i in 0..6) {
            stdout.print(counter[i])
            stdout.print(if (i == 6) '\n' else ' ')
        }
    }

    private fun isLeap(year: Int): Boolean {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0
    }
}
