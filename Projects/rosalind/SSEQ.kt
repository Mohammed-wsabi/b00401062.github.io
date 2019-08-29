import java.io.*
import java.util.*

object SSEQ {
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
        val seq = reads.get(0)
        val pat = reads.get(1)
        var i = 0
        var j = 0
        while (j < pat.length()) {
            while (i < seq.length()) {
                if (seq.charAt(i) === pat.charAt(j)) {
                    System.out.println(++i)
                    break
                }
                i++
            }
            j++
        }
        stdin.close()
    }
}
