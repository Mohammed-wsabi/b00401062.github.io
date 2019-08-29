import java.io.*
import java.util.*

object SCSP {
    private fun scs(s: String, t: String): String {
        val table = Array(s.length() + 1) { IntArray(t.length() + 1) }
        for (i in 1..s.length())
            for (j in 1..t.length())
                table[i][j] = if (s.charAt(i - 1) === t.charAt(j - 1)) table[i - 1][j - 1] + 1 else Math.max(table[i - 1][j], table[i][j - 1])
        val scs = StringBuilder()
        var i = s.length()
        var j = t.length()
        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) === t.charAt(j - 1)) {
                scs.append(s.charAt(i - 1))
                i--
                j--
            } else if (table[i][j] == table[i - 1][j]) {
                scs.append(s.charAt(i - 1))
                i--
            } else if (table[i][j] == table[i][j - 1]) {
                scs.append(t.charAt(j - 1))
                j--
            }
        }
        return s.substring(0, i) + t.substring(0, j) + scs.reverse().toString()
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        System.out.println(scs(stdin.next(), stdin.next()))
        stdin.close()
    }
}
