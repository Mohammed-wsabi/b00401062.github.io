/*
ID: rombin82
LANG: JAVA
TASK: skidesign
*/

import java.io.*
import java.util.*

object skidesign {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("skidesign.in"))
        val stdout = PrintWriter(File("skidesign.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        val hills = IntArray(N)
        for (i in 0 until N)
            hills[i] = stdin.nextInt()
        Arrays.sort(hills)
        var cost_min = Integer.MAX_VALUE
        for (i in 0..83) {
            var cost = 0
            for (j in 0 until N) {
                var diff = 0
                if (hills[j] < i)
                    diff = i - hills[j]
                else if (hills[j] > i + 17)
                    diff = hills[j] - (i + 17)
                cost += diff * diff
            }
            cost_min = Math.min(cost_min, cost)
        }
        stdout.println(cost_min)
    }
}
