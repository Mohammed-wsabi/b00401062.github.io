/*
ID: rombin82
LANG: JAVA
TASK: test
*/

import java.io.*
import java.util.*

object test {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("test.in"))
        val stdout = PrintWriter(File("test.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        stdout.println(stdin.nextInt() + stdin.nextInt())
    }
}
