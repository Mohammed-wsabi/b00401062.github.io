/*
ID: rombin82
LANG: JAVA
TASK: crypt1
*/

import java.io.*
import java.util.*

object crypt1 {
    private var digits: List<Integer>? = null
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("crypt1.in"))
        val stdout = PrintWriter(File("crypt1.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        digits = ArrayList<Integer>(N)
        for (i in 0 until N)
            digits!!.add(stdin.nextInt())
        var count = 0
        var elem = 0
        val stack = ArrayList<Integer>(N)
        do {
            stack.add(digits!![digits!!.indexOf(elem) + 1])
            for (i in stack.size()..4)
                stack.add(digits!![0])
            val n1 = 100 * stack.get(0) + 10 * stack.get(1) + stack.get(2)
            val n2 = 10 * stack.get(3) + stack.get(4)
            val p1 = n1 * stack.get(4)
            val p2 = n1 * stack.get(3)
            val sum = n1 * n2
            if (p1 < 1000 && p2 < 1000 && isCrypt(p1) && isCrypt(p2) && isCrypt(sum))
                count++
            while (stack.size() > 0 && (elem = stack.remove(stack.size() - 1)) == digits!![N - 1]);
        } while (elem != digits!![N - 1])
        stdout.println(count)
    }

    private fun isCrypt(num: Int): Boolean {
        var num = num
        while (num > 0) {
            if (!digits!!.contains(num % 10))
                return false
            num /= 10
        }
        return true
    }
}
