/*
ID: rombin82
LANG: JAVA
TASK: milk2
*/

import java.io.*
import java.util.*

object milk2 {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("milk2.in"))
        val stdout = PrintWriter(File("milk2.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        val farmers = Array(N) { IntArray(2) }
        for (i in 0 until N) {
            farmers[i][0] = stdin.nextInt()
            farmers[i][1] = stdin.nextInt()
        }
        Arrays.sort(farmers, Comparator.comparingInt({ a -> a[0] }))
        var max_idle = 0
        var max_work = 0
        var ts = farmers[0][0]
        var te = farmers[0][1]
        for (i in 1 until N) {
            if (te < farmers[i][0]) {
                max_idle = Math.max(max_idle, farmers[i][0] - te)
                max_work = Math.max(max_work, te - ts)
                ts = farmers[i][0]
                te = farmers[i][1]
            } else {
                te = Math.max(te, farmers[i][1])
            }
        }
        max_work = Math.max(max_work, te - ts)
        stdout.println("$max_work $max_idle")
    }
}
