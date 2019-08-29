/*
ID: rombin82
LANG: JAVA
TASK: milk
*/

import java.io.*
import java.util.*

object milk {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("milk.in"))
        val stdout = PrintWriter(File("milk.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        var N = stdin.nextInt()
        val M = stdin.nextInt()
        val farmers = Array(M) { IntArray(2) }
        for (i in 0 until M) {
            farmers[i][0] = stdin.nextInt()
            farmers[i][1] = stdin.nextInt()
        }
        Arrays.sort(farmers, { a, b -> a[0] - b[0] })
        var cost = 0
        var i = 0
        while (i < M && 0 < N) {
            cost += Math.min(N, farmers[i][1]) * farmers[i][0]
            N -= farmers[i][1]
            i++
        }
        stdout.println(cost)
    }
}
