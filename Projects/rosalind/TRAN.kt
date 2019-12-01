package rosalind

import java.io.IOException
import java.util.*

object TRAN {
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
        val s1 = reads[0]
        val s2 = reads[1]
        var transitions = 0
        var transversions = 0
        for (i in s1!!.indices) {
            if (s1[i] == s2!![i]) continue
            else if (
                s1[i] == 'A' && s2[i] == 'G' ||
                s1[i] == 'G' && s2[i] == 'A' ||
                s1[i] == 'C' && s2[i] == 'T' ||
                s1[i] == 'T' && s2[i] == 'C'
            ) transitions++
            else transversions++
        }
        println(transitions.toDouble() / transversions)
        stdin.close()
    }
}