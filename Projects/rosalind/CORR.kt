package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

object CORR {
    private val COMPLEMENT: Map<Char, Char> = mapOf(
        'A' to 'T', 'C' to 'G', 'G' to 'C', 'T' to 'A'
    )

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads: MutableSet<String> = HashSet()
        val table: MutableMap<String, Int?> = HashMap()
        while (stdin.hasNext()) {
            stdin.next()
            val read = stdin.next()
            val revc = StringBuilder(read).
                reverse().
                chars().
                mapToObj(Int::toChar).
                map { o: Char? -> COMPLEMENT[o] }.
                map { obj: Char? -> obj.toString() }.
                collect(Collectors.joining())
            reads.add(read)
            table[read] = if (table.containsKey(read)) table[read]!! + 1 else 1
            table[revc] = if (table.containsKey(revc)) table[revc]!! + 1 else 1
        }
        val n = reads.toTypedArray()[0].length
        for (pat in reads) if (table[pat] == 1) {
            for (seq in table.keys) {
                if (
                    table[seq]!! > 1 &&
                    (0 until n).filterNot { seq[it] == pat[it] }.count() == 1
                ) {
                    println("$pat->$seq")
                    break
                }
            }
        }
        stdin.close()
    }
}