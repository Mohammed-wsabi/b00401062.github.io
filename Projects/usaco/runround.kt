/*
ID: rombin82
LANG: JAVA
TASK: runround
*/

import java.io.*
import java.util.*

object runround {
    private fun isRunRound(num: String): Boolean {
        val covered = HashSet<Integer>()
        val num_len = num.length()
        var digit = num.charAt(0) - '0'
        var cnt = 0
        var pos = 0
        while (cnt < num_len) {
            pos = (digit + pos) % num_len
            digit = num.charAt(pos) - '0'
            if (covered.contains(digit) || digit == 0)
                return false
            covered.add(digit)
            cnt++
        }
        return true
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("runround.in"))
        val stdout = PrintWriter(File("runround.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        var n = stdin.nextInt()
        n++
        while (!isRunRound(Integer.toString(n))) {
            n++
        }
        stdout.println(n)
    }
}
