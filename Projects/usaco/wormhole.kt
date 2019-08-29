/*
ID: rombin82
LANG: JAVA
TASK: wormhole
*/

import java.io.*
import java.util.*

object wormhole {
    private var N: Int = 0
    private val X = IntArray(13)
    private val Y = IntArray(13)
    private val partner = IntArray(13)
    private val next_on_right = IntArray(13)
    private val isCycle: Boolean
        get() {
            for (start in 1..N) {
                var pos = start
                for (count in 0 until N)
                    pos = next_on_right[partner[pos]]
                if (pos != 0)
                    return true
            }
            return false
        }

    private fun solve(): Int {
        var i: Int
        var count = 0
        i = 1
        while (i <= N) {
            if (partner[i] == 0)
                break
            i++
        }
        if (i > N)
            return if (isCycle) 1 else 0
        for (j in i + 1..N)
            if (partner[j] == 0) {
                partner[i] = j
                partner[j] = i
                count += solve()
                partner[j] = 0
                partner[i] = partner[j]
            }
        return count

    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("wormhole.in"))
        val stdout = PrintWriter(File("wormhole.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        N = stdin.nextInt()
        for (i in 1..N) {
            X[i] = stdin.nextInt()
            Y[i] = stdin.nextInt()
        }
        for (i in 1..N)
            for (j in 1..N)
                if (X[j] > X[i] && Y[i] == Y[j])
                    if (next_on_right[i] == 0 || X[j] < X[next_on_right[i]])
                        next_on_right[i] = j
        stdout.println(solve())
    }
}
