/*
ID: rombin82
LANG: JAVA
TASK: ride
*/

import java.io.*
import java.util.*

object ride {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("ride.in"))
        val stdout = PrintWriter(File("ride.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val comet = stdin.next()
        val group = stdin.next()
        stdout.println(if (hash(comet) == hash(group)) "GO" else "STAY")
    }

    private fun hash(str: String): Int {
        var hash = 1
        for (i in 0 until str.length())
            hash = hash * (str.charAt(i) - 'A' + 1) % 47
        return hash
    }
}
