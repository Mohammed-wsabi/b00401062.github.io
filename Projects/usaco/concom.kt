/*
ID: rombin82
LANG: JAVA
TASK: concom
*/

import java.io.*
import java.util.*

object concom {
    private val MAX = 100
    private val table = Array(MAX + 1) { IntArray(MAX + 1) }
    private var counts: IntArray? = null
    private var visited: BooleanArray? = null
    private fun visit(i: Int) {
        visited[i] = true
        for (j in 1..MAX)
            counts[j] += table[i][j]
        for (j in 1..MAX)
            if (counts!![j] > 50 && !visited!![j])
                visit(j)
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("concom.in"))
        val stdout = PrintWriter(File("concom.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        for (n in 0 until N)
            table[stdin.nextInt()][stdin.nextInt()] = stdin.nextInt()
        for (i in 1..MAX) {
            counts = IntArray(MAX + 1)
            visited = BooleanArray(MAX + 1)
            visit(i)
            for (j in 1..MAX)
                if (counts!![j] > 50 && j != i)
                    stdout.println("$i $j")
        }
    }
}
