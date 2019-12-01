package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors

object DBRU {
    private val COMPLEMENT: Map<Char, Char> = mapOf(
        'A' to 'T', 'C' to 'G', 'G' to 'C', 'T' to 'A'
    )

    private fun rc(seq: String): String {
        return StringBuilder(seq).
            reverse().
            chars().
            mapToObj { x: Int -> x.toChar() }.
            map { o: Char? -> COMPLEMENT[o] }.
            map { obj: Char? -> obj.toString() }.
            collect(Collectors.joining())
    }

    private fun add(map: MutableMap<String, MutableSet<String>?>, seq: String) {
        val k = seq.substring(0, seq.length - 1)
        val v = seq.substring(1)
        if (map.containsKey(k)) map[k]!!.add(v) else map[k] = mutableSetOf(v)
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val map: MutableMap<String, MutableSet<String>?> = HashMap()
        while (stdin.hasNextLine()) {
            val seq = stdin.nextLine()
            add(map, seq)
            add(map, rc(seq))
        }
        for ((k, value) in map) {
            for (v in value!!) System.out.printf("(%s, %s)\n", k, v)
        }
        stdin.close()
    }
}