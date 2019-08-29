/*
ID: rombin82
LANG: JAVA
TASK: sort3
*/

import java.io.*
import java.util.*

object sort3 {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("sort3.in"))
        val stdout = PrintWriter(File("sort3.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val n = stdin.nextInt()
        val seq = IntArray(n)
        val sc = IntArray(4)
        for (i in 0 until n)
            sc[seq[i] = stdin.nextInt()]++
        var s12 = 0
        var s13 = 0
        var s21 = 0
        var s31 = 0
        var s23 = 0
        var s32 = 0
        for (i in 0 until sc[1]) {
            if (seq[i] == 2) s12++
            if (seq[i] == 3) s13++
        }
        for (i in sc[1] until sc[1] + sc[2]) {
            if (seq[i] == 1) s21++
            if (seq[i] == 3) s23++
        }
        for (i in sc[1] + sc[2] until sc[1] + sc[2] + sc[3]) {
            if (seq[i] == 1) s31++
            if (seq[i] == 2) s32++
        }
        stdout.println(Math.min(s12, s21) + Math.min(s13, s31) + Math.min(s23, s32) + 2 * Math.abs(s12 - s21))
    }
}
