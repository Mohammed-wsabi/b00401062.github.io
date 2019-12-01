package rosalind

import java.io.IOException
import java.util.*

object SSEQ {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads: MutableList<String?> = ArrayList()
        var read: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                reads.add(read)
                break
            }
            val line = stdin.next()
            if (line[0] == '>') {
                if (read != null) reads.add(read)
                read = ""
            } else read += line
        }
        val seq = reads[0]
        val pat = reads[1]
        var i = 0
        var j = 0
        while (j < pat!!.length) {
            while (i < seq!!.length) {
                if (seq[i] == pat[j]) {
                    println(++i)
                    break
                }
                i++
            }
            j++
        }
        stdin.close()
    }
}