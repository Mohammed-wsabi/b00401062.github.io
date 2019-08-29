import java.io.*
import java.util.*

object KMP {
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

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val read = StringBuilder()
        stdin.next()
        while (stdin.hasNext())
            read.append(stdin.next())
        Arrays.stream(preprocess(read.toString())).map({ x -> x + 1 }).forEach(???({ System.out.println() }))
    }
}
