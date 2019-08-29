/*
ID: rombin82
LANG: JAVA
TASK: barn1
*/

import java.io.*
import java.util.*

object barn1 {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("barn1.in"))
        val stdout = PrintWriter(File("barn1.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val M = stdin.nextInt()
        val S = stdin.nextInt()
        val C = stdin.nextInt()
        if (M >= C) {
            stdout.println(C)
            System.exit(0)
        }
        val cows = IntArray(C)
        for (i in 0 until C)
            cows[i] = stdin.nextInt()
        Arrays.sort(cows)
        val heap = PriorityQueue<IntArray>(C - 1) { a, b -> b[1] - a[1] }
        for (i in 1 until C)
            heap.add(intArrayOf(i, cows[i] - cows[i - 1]))
        val breakpoint = IntArray(M + 1)
        for (i in 1 until M)
            breakpoint[i] = heap.poll()[0]
        breakpoint[M] = C
        Arrays.sort(breakpoint)
        var count = 0
        for (i in 1 until breakpoint.size)
            count += cows[breakpoint[i] - 1] - cows[breakpoint[i - 1]] + 1
        stdout.println(count)
    }
}
