package rosalind

import java.io.IOException
import java.util.*

object SORT {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val s = stdin.nextLine().
            split(" ").
            map(String::toInt)
        val t = stdin.nextLine().
            split(" ").
            map(String::toInt)
        val steps: MutableList<IntArray> = ArrayList()
        for (i in 0..9) {
            if (s[i] == t[i]) continue
            val x = s.indexOf(t[i])
            Collections.reverse(s.subList(i, x + 1))
            steps.add(intArrayOf(i + 1, x + 1))
        }
        println(steps.size)
        for (step in steps) {
            println("${step[0]} ${step[1]}")
        }
        stdin.close()
    }
}