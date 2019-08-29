/*
ID: rombin82
LANG: JAVA
TASK: ariprog
*/

import java.io.*
import java.util.*

object ariprog {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("ariprog.in"))
        val stdout = PrintWriter(File("ariprog.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val n = stdin.nextInt()
        val m = stdin.nextInt()
        val isBisquare = BooleanArray(m * m * 2 + 1)
        for (p in 0..m)
            for (q in p..m)
                isBisquare[p * p + q * q] = true
        var none = true
        for (r in 1..m * m * 2) {
            var a = 0
            while (a + r * (n - 1) <= m * m * 2) {
                var isValid = true
                var b = 0
                while (b < n) {
                    if (a + b * r >= isBisquare.size || !isBisquare[a + b * r]) {
                        isValid = false
                        b = n
                    }
                    b++
                }
                if (isValid) {
                    stdout.println("$a $r")
                    none = false
                }
                a++
            }
        }
        if (none)
            stdout.println("NONE")
    }
}
