/*
ID: rombin82
LANG: JAVA
TASK: zerosum
*/

import java.io.*
import java.util.*

object zerosum {
    private var N: Int = 0
    private val exprs = ArrayList()
    private fun evaluate(expr: String): Int {
        val tokens = expr.replaceAll(" ", "").replaceAll("([+|-])", " $1 ").split(" ")
        var sum = Integer.parseInt(tokens[0])
        var i = 1
        while (i < tokens.size) {
            if (tokens[i].equals("+"))
                sum += Integer.parseInt(tokens[i + 1])
            else
                sum -= Integer.parseInt(tokens[i + 1])
            i += 2
        }
        return sum
    }

    private fun recur(n: Int, expr: String) {
        if (n > N) {
            if (evaluate(expr) == 0)
                exprs.add(expr)
            return
        }
        for (opr in charArrayOf('+', '-', ' '))
            recur(n + 1, expr.toInt() + opr.toInt() + n)
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("zerosum.in"))
        val stdout = PrintWriter(File("zerosum.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        N = stdin.nextInt()
        stdin.close()
        recur(2, "1")
        Collections.sort(exprs)
        for (expr in exprs)
            stdout.println(expr)
    }
}
