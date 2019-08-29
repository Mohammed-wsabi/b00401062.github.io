/*
ID: rombin82
LANG: JAVA
TASK: hamming
*/

import java.io.*
import java.util.*

object hamming {
    private var N: Int = 0
    private var B: Int = 0
    private var D: Int = 0
    private var dist: Array<IntArray>? = null
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("hamming.in"))
        val stdout = PrintWriter(File("hamming.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        N = stdin.nextInt()
        B = stdin.nextInt()
        D = stdin.nextInt()
        dist = Array(1 shl B) { IntArray(1 shl B) }
        for (i in 0 until (1 shl B))
            for (j in i + 1 until (1 shl B))
                for (s in 0 until B)
                    if (i xor j and (1 shl s) != 0)
                        dist!![i][j] = ++dist!![j][i]
        val codes = ArrayList<Integer>(N)
        codes.add(0)
        run {
            var i = 1
            while (i < 1 shl B && codes.size() < N) {
                var valid = true
                for (j in 0 until codes.size())
                    if (dist!![codes.get(j)][i] < D) {
                        valid = false
                        break
                    }
                if (valid)
                    codes.add(i)
                i++
            }
        }
        for (i in 0 until N) {
            stdout.print(codes.get(i))
            stdout.print(if (i % 10 == 9 || i == N - 1) '\n' else ' ')
        }
    }
}
