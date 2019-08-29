/*
ID: rombin82
LANG: JAVA
TASK: numtri
*/

import java.io.*
import java.util.*

object numtri {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("numtri.in"))
        val stdout = PrintWriter(File("numtri.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        val triangle = arrayOfNulls<IntArray>(N)
        for (row in 0 until N) {
            triangle[row] = IntArray(row + 1)
            for (col in 0..row) {
                triangle[row][col] = stdin.nextInt()
            }
        }
        for (row in N - 2 downTo 0)
            for (col in 0 until triangle[row].size)
                triangle[row][col] += Math.max(triangle[row + 1][col], triangle[row + 1][col + 1])
        stdout.println(triangle[0][0])
    }
}
