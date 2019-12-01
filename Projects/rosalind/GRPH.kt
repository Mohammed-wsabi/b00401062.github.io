package rosalind

import java.io.IOException
import java.util.*

object GRPH {
    private fun seq2num(seq: String): Int {
        var num = 0
        for (i in 0 until seq.length) {
            num = num * 4 + "ACGT".indexOf(seq[i])
        }
        return num
    }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val table = Array<Array<ArrayList<String?>?>>(64) { arrayOfNulls(2) }
        var name: String? = null
        var seq: String? = null
        for (i in 0..63) {
            for (j in 0..1) {
                table[i][j] = ArrayList()
            }
        }
        while (true) {
            if (!stdin.hasNext()) {
                table[seq2num(seq!!.substring(0, 3))][1]?.add(name)
                table[seq2num(seq.substring(seq.length - 3, seq.length))][0]?.add(name)
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (name != null) {
                    table[seq2num(seq!!.substring(0, 3))][1]?.add(name)
                    table[seq2num(seq.substring(seq.length - 3, seq.length))][0]?.add(name)
                }
                name = line.substring(1)
                seq = ""
            } else seq += line
        }
        for (i in 0..63) {
            if (table[i][0]?.size != 0 && table[i][1]?.size != 0) {
                for (s in table[i][0]!!) {
                    for (t in table[i][1]!!) {
                        if (s != t) {
                            println("$s $t")
                        }
                    }
                }
            }
        }
        stdin.close()
    }
}