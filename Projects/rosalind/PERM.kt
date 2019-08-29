import java.io.*
import java.util.*
import java.util.stream.*

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
        s[l] = s[l] xor (s[r] xor (s[r] = s[l]))
        for (i in 1..(n - l) / 2)
            s[l + i] = s[l + i] xor (s[n - i] xor (s[n - i] = s[l + i]))
        return true
    }

    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        val n = stdin.nextInt()
        val N = factorial(n)
        stdout.println(N)
        val s = IntStream.rangeClosed(1, n).toArray()
        do {
            stdout.println(Arrays.stream(s).mapToObj(???({ String.valueOf() })).collect(Collectors.joining(" ")))
        } while (next(s))
        stdin.close()
    }
}
