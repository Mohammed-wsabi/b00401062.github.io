/*
ID: rombin82
LANG: JAVA
TASK: transform
*/

import java.io.*
import java.util.*

object transform {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("transform.in"))
        val stdout = PrintWriter(File("transform.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.close() })
        val N = stdin.nextInt()
        val before = Array(N) { CharArray(N) }
        val after = Array(N) { CharArray(N) }
        for (i in 0 until N)
            before[i] = stdin.next().toCharArray()
        for (i in 0 until N)
            after[i] = stdin.next().toCharArray()
        val rt090 = rotate(before)
        val rt180 = rotate(rt090)
        val rt270 = rotate(rt180)
        if (Arrays.deepEquals(after, rt090))
            stdout.println('1')
        else if (Arrays.deepEquals(after, rt180))
            stdout.println('2')
        else if (Arrays.deepEquals(after, rt270))
            stdout.println('3')
        else if (Arrays.deepEquals(after, reflect(before)))
            stdout.println('4')
        else if (Arrays.deepEquals(after, reflect(rt090)))
            stdout.println('5')
        else if (Arrays.deepEquals(after, reflect(rt180)))
            stdout.println('5')
        else if (Arrays.deepEquals(after, reflect(rt270)))
            stdout.println('5')
        else if (Arrays.deepEquals(after, before))
            stdout.println('6')
        else
            stdout.println('7')
    }

    private fun rotate(before: Array<CharArray>): Array<CharArray> {
        val N = before.size
        val after = Array(N) { CharArray(N) }
        for (i in 0 until N)
            for (j in 0 until N)
                after[j][N - i - 1] = before[i][j]
        return after
    }

    private fun reflect(before: Array<CharArray>): Array<CharArray> {
        val N = before.size
        val after = Array(N) { CharArray(N) }
        for (i in 0 until N)
            for (j in 0 until N)
                after[i][N - j - 1] = before[i][j]
        return after
    }
}
