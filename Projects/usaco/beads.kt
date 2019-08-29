/*
ID: rombin82
LANG: JAVA
TASK: beads
*/

import java.io.*
import java.util.*

object beads {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("beads.in"))
        val stdout = PrintWriter(File("beads.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        var max = 0
        val N = stdin.nextInt()
        var necklace = stdin.next()
        necklace += necklace
        for (i in 0 until N) {
            var c = necklace.charAt(i)
            var state = if (c == 'w') 0 else 1
            var current = 0
            var j = i
            while (state <= 2) {
                while (j < N + i && (necklace.charAt(j) === c || necklace.charAt(j) === 'w')) {
                    current++
                    j++
                }
                state++
                c = necklace.charAt(j)
            }
            max = Math.max(max, current)
        }
        stdout.println(max)
    }
}
