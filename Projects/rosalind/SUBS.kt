import java.io.*
import java.util.*

object SUBS {
    private fun preprocess(t: String): IntArray {
        val table = IntArray(t.length())
        table[0] = -1
        var k = -1
        for (i in 1 until t.length()) {
            while (k >= 0 && t.charAt(k + 1) !== t.charAt(i))
                k = table[k]
            if (t.charAt(k + 1) === t.charAt(i))
                k++
            table[i] = k
        }
        return table
    }

    private fun match(s: String, t: String, table: IntArray): IntArray {
        val indices = ArrayList()
        var k = -1
        for (i in 0 until s.length()) {
            while (k > 0 && t.charAt(k + 1) !== s.charAt(i))
                k = table[k]
            if (t.charAt(k + 1) === s.charAt(i))
                k++
            if (k == t.length() - 1) {
                indices.add(i + 1 - t.length())
                k = table[k]
            }
        }
        return indices.stream().mapToInt(???({ Integer.valueOf() })).toArray()
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val s = stdin.next()
        val t = stdin.next()
        Arrays.stream(match(s, t, preprocess(t))).map({ x -> x + 1 }).forEach(???({ System.out.println() }))
        stdin.close()
    }
}
