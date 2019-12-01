package rosalind

import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object PERM {
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

    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        val n = stdin.nextInt()
        val N = factorial(n)
        stdout.println(N)
        val s = IntStream.rangeClosed(1, n).toArray()
        do {
            stdout.println(Arrays.stream(s).mapToObj { i: Int -> i.toString() }.collect(Collectors.joining(" ")))
        } while (next(s))
        stdin.close()
    }
}