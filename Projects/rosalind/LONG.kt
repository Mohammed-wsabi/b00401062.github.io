package rosalind

import java.io.IOException
import java.util.*

object LONG {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads: MutableList<String?> = ArrayList()
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                reads.add(seq)
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (seq != null) reads.add(seq)
                seq = ""
            } else seq += line
        }
        reads.sortWith(Collections.reverseOrder(Comparator.comparingInt { obj: String -> obj.length }))
        var genome = reads.removeAt(0)
        while (reads.size > 0) {
            for (i in reads.indices) {
                val read = reads[i]
                var matched = false
                for (j in 0 until read!!.length / 2) {
                    if (read.substring(j, read.length) == genome!!.substring(0, read.length - j)) {
                        genome = read.substring(0, j) + genome
                        matched = true
                        break
                    }
                }
                if (matched) {
                    reads.removeAt(i)
                    break
                }
                for (j in read.length / 2..read.length) if (read.substring(0, j) == genome!!.substring(genome.length - j)) {
                    genome += read.substring(j)
                    matched = true
                    break
                }
                if (matched) {
                    reads.removeAt(i)
                    break
                }
            }
        }
        println(genome)
        stdin.close()
    }
}