import java.io.*
import java.util.*
import java.util.stream.*

object PDST {
    private fun hamming(s: String, t: String): Long {
        val l = s.length()
        return IntStream.range(0, l).filter({ i -> s.charAt(i) !== t.charAt(i) }).count()
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads = ArrayList()
        var read: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                reads.add(read)
                break
            }
            val line = stdin.next()
            if (line.charAt(0) === '>') {
                if (read != null)
                    reads.add(read)
                read = ""
            } else
                read += line
        }
        val n = reads.size()
        val l = reads.get(0).length()
        val d = Array(n) { DoubleArray(n) }
        for (i in 0 until n)
            for (j in i + 1 until n) {
                d[j][i] = hamming(reads.get(i), reads.get(j)).toDouble() / l
                d[i][j] = d[j][i]
            }
        for (i in 0 until n)
            System.out.println(Arrays.stream(d[i]).mapToObj(???({ String.valueOf() })).collect(Collectors.joining(" ")))
    }
}
