/*
ID: rombin82
LANG: JAVA
TASK: dualpal
*/

import java.io.*
import java.util.*

object dualpal {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("dualpal.in"))
        val stdout = PrintWriter(File("dualpal.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        var N = stdin.nextInt()
        var S = stdin.nextInt()
        while (N-- > 0) {
            var flag = true
            while (flag) {
                val num = ++S
                if (isDualPal(num)) {
                    stdout.println(num)
                    flag = false
                    break
                }
            }
        }
    }

    private fun isDualPal(num: Int): Boolean {
        var count = 0
        for (b in 2..10)
            if (isPalindrome(Integer.toString(num, b)))
                count++
        return count >= 2
    }

    private fun isPalindrome(str: String): Boolean {
        return StringBuffer(str).reverse().toString().equals(str)
    }
}
