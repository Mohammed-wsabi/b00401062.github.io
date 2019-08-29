/*
ID: rombin82
LANG: JAVA
TASK: pprime
*/

import java.io.*
import java.util.*

object pprime {
    private var a: Int = 0
    private var b: Int = 0
    private var stdout: PrintWriter? = null
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("pprime.in"))
        stdout = PrintWriter(File("pprime.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout!!.flush() })
        a = stdin.nextInt()
        b = stdin.nextInt()
        for (digits in String.valueOf(a).length()..String.valueOf(b).length())
            generatePPrime("", digits)
    }

    private fun generatePPrime(current: String, digits: Int) {
        var current = current
        if (current.equals("0"))
            return
        if (current.length() === (digits + 1) / 2) {
            for (i in digits / 2 - 1 downTo 0)
                current += current.charAt(i)
            val currentInt = Integer.parseInt(current)
            if (isPrime(currentInt) && currentInt >= a && currentInt <= b)
                stdout!!.println(current)
            return
        }
        for (nextDigit in 0..9)
            if (!current.equals("") || nextDigit % 2 == 1)
                generatePPrime(current + nextDigit, digits)
    }

    private fun isPrime(n: Int): Boolean {
        if (n == 2)
            return true
        if (n == 1 || n % 2 == 0)
            return false
        var i = 3
        while (i <= Math.sqrt(n)) {
            if (n % i == 0)
                return false
            i += 2
        }
        return true
    }
}
