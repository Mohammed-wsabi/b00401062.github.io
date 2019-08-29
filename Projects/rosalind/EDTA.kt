import java.io.*
import java.util.*
import java.util.stream.*

object EDTA {
    private fun lcs(s: String, t: String): Array<String> {
        val table = Array(s.length() + 1) { IntArray(t.length() + 1) }
        for (i in 1..s.length())
            table[i][0] = i
        for (j in 1..t.length())
            table[0][j] = j
        for (i in 1..s.length())
            for (j in 1..t.length()) {
                table[i][j] = Arrays.stream(intArrayOf(table[i - 1][j - 1] + if (s.charAt(i - 1) === t.charAt(j - 1)) 0 else 1, table[i - 1][j] + 1, table[i][j - 1] + 1)).min().getAsInt()
            }
        val lcs = arrayOf<StringBuilder>(StringBuilder(), StringBuilder())
        var i = s.length()
        var j = t.length()
        while (i > 0 && j > 0) {
            if (table[i][j] == table[i - 1][j - 1] + (if (s.charAt(i - 1) === t.charAt(j - 1)) 0 else 1)) {
                lcs[0].append(s.charAt(--i))
                lcs[1].append(t.charAt(--j))
            } else if (table[i][j] == table[i - 1][j] + 1) {
                lcs[0].append(s.charAt(--i))
                lcs[1].append('-')
            } else if (table[i][j] == table[i][j - 1] + 1) {
                lcs[0].append('-')
                lcs[1].append(t.charAt(--j))
            }
        }
        System.out.println(table[s.length()][t.length()])
        return arrayOf(lcs[0].reverse().toString(), lcs[1].reverse().toString())
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var s = ""
        var t = ""
        var line = stdin.next()
        while (stdin.hasNext()) {
            if ((line = stdin.next()).charAt(0) === '>')
                break
            s += line
        }
        while (stdin.hasNext())
            t += stdin.next()
        val lcs = lcs(s, t)
        Arrays.stream(lcs).forEach(???({ System.out.println() }))
    }
}
