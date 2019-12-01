package rosalind

import java.util.*

object REVP {
    private val COMPLEMENT: Map<Char, Char> = mapOf(
        'A' to 'T', 'C' to 'G', 'G' to 'C', 'T' to 'A'
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var seq = ""
        stdin.next()
        while (stdin.hasNext()) seq += stdin.next()
        for (s in 0 until seq.length - 1) if (COMPLEMENT[seq[s]] == seq[s + 1]) {
            var i = 1
            while (s >= i && s + i + 1 < seq.length && COMPLEMENT[seq[s - i]] == seq[s + i + 1]) {
                if (4 <= 2 * i + 2 && 2 * i + 2 <= 12) {
                    println(String.format("%d %d", s - i + 1, 2 * i + 2))
                }
                i++
            }
        }
        stdin.close()
    }
}