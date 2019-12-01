package rosalind

import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object SIGN {
    private fun factorial(i: Int): Int {
        return if (i == 0) 1 else i * factorial(i - 1)
    }

    private fun next(s: IntArray): Boolean {
        val n = s.size
        var l = n - 2
        var r = n - 1
        while (l >= 0 && s[l] >= s[l + 1]) {
            l--
        }
        if (l < 0) return false
        while (r >= l && s[l] >= s[r]) {
            r--
        }
        s[l] = s[l] xor s[r] xor s[l].also { s[r] = it }
        for (i in 1..(n - l) / 2) s[l + i] = s[l + i] xor s[n - i] xor s[l + i].also { s[n - i] = it }
        return true
    }

    private fun abuse(s: IntArray, l: Int) {
        if (l == s.size) {
            println(Arrays.stream(s).mapToObj { i: Int -> i.toString() }.collect(Collectors.joining(" ")))
            return
        }
        abuse(s, l + 1)
        s[l] = -s[l]
        abuse(s, l + 1)
        s[l] = -s[l]
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        val n = stdin.nextInt()
        stdout.println(factorial(n) * Math.pow(2.0, n.toDouble()).toInt())
        val s = IntStream.rangeClosed(1, n).toArray()
        do {
            abuse(s, 0)
        } while (next(s))
        stdin.close()
    }
}