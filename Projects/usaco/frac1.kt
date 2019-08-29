/*
ID: rombin82
LANG: JAVA
TASK: frac1
*/

import java.io.*
import java.util.*

object frac1 {
    private var stdout: PrintWriter? = null
    private var n: Int = 0
    private fun generateFrac(n1: Int, d1: Int, n2: Int, d2: Int) {
        if (d1 + d2 > n)
            return
        generateFrac(n1, d1, n1 + n2, d1 + d2)
        stdout!!.println((n1 + n2).toString() + "/" + (d1 + d2))
        generateFrac(n1 + n2, d1 + d2, n2, d2)
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("frac1.in"))
        stdout = PrintWriter(File("frac1.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout!!.flush() })
        n = stdin.nextInt()
        stdout!!.println("0/1")
        generateFrac(0, 1, 1, 1)
        stdout!!.println("1/1")
    }
}
