/*
ID: rombin82
LANG: JAVA
TASK: preface
*/

import java.io.*
import java.util.*

object preface {
    private val keys = intArrayOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    private val values = arrayOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    private val dict = HashMap(13)
    private fun toRoman(num: Int): String {
        var num = num
        var roman = ""
        while (num > 0)
            for (i in keys.indices.reversed())
                if (num >= keys[i]) {
                    num -= keys[i]
                    roman += dict.get(keys[i])
                    break
                }
        return roman
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(File("preface.in"))
        val stdout = PrintWriter(File("preface.out"))
        Runtime.getRuntime().addShutdownHook(Thread { stdout.flush() })
        val N = stdin.nextInt()
        for (i in keys.indices)
            dict.put(keys[i], values[i])
        val counter = IntArray(26)
        for (i in 1..N) {
            val str = toRoman(i)
            str.chars().forEach({ chr -> counter[chr - 'A']++ })
        }
        for (chr in charArrayOf('I', 'V', 'X', 'L', 'C', 'D', 'M'))
            if (counter[chr - 'A'] > 0)
                stdout.println(chr + " " + counter[chr - 'A'])
    }
}
