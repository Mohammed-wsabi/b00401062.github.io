import java.io.*
import java.util.*

internal object CLFIBD {
    private fun check(c: IntArray): Boolean {
        var c = c
        c = Arrays.stream(c).filter({ i -> i !== 0 }).toArray()
        Arrays.sort(c)
        var flag = true
        for (i in 0 until c.size - 2)
            if (c[i] + c[i + 1] != c[i + 2])
                flag = false
        if (flag) return true
        c[0] = c[0] xor (c[1] xor (c[1] = c[0]))
        for (i in 0 until c.size - 2)
            if (c[i] + c[i + 1] != c[i + 2])
                return false
        return true
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var t = stdin.nextInt()
        while (t-- > 0) {
            val c = IntArray(26)
            stdin.next().chars().forEach({ i -> c[i - 'a']++ })
            System.out.println(if (check(c)) "Dynamic" else "Not")
        }
    }
}
