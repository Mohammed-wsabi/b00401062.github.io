package rosalind

import java.io.IOException
import java.util.*
import java.util.stream.Collectors

object CONS {
    private fun put(table: Array<IntArray>, seq: String) {
        for (i in table.indices) {
            table[i]["ACGT".indexOf(seq[i])]++
        }
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var table: Array<IntArray>? = null
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                put(table!!, seq!!)
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (seq != null) {
                    if (table == null) table = Array(seq.length) { IntArray(4) }
                    put(table, seq)
                }
                seq = ""
            } else seq += line
        }
        for (i in table!!.indices) {
            val max = Arrays.stream(table[i]).max().asInt
            print("ACGT"[Arrays.stream(table[i]).boxed().collect(Collectors.toList()).indexOf(max)])
        }
        println()
        for (j in 0..3) {
            print(String.format("%c:", "ACGT"[j]))
            for (i in table.indices) print(String.format(" %d", table[i][j]))
            println()
        }
        stdin.close()
    }
}