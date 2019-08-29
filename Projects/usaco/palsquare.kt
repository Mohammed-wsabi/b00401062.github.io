/*
ID: rombin82
LANG: JAVA
TASK: palsquare
*/

import java.io.*
import java.util.*

object palsquare {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("palsquare.in"))
        val stdout = PrintWriter(File("palsquare.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val B = stdin.nextInt()
        for (i in 1..300)
            if (isPalindrome(Integer.toString(i * i, B)))
                stdout.println(Integer.toString(i, B).toUpperCase() + " " + Integer.toString(i * i, B).toUpperCase())
    }

    private fun isPalindrome(str: String): Boolean {
        return StringBuffer(str).reverse().toString().equals(str)
    }
}
