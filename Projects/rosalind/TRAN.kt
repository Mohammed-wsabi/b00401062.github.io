import java.io.*
import java.util.*

object TRAN {
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
        val s1 = reads.get(0)
        val s2 = reads.get(1)
        var transitions = 0
        var transversions = 0
        for (i in 0 until s1.length())
            if (s1.charAt(i) === s2.charAt(i))
                continue
            else if (s1.charAt(i) === 'A' && s2.charAt(i) === 'G' || s1.charAt(i) === 'G' && s2.charAt(i) === 'A' || s1.charAt(i) === 'C' && s2.charAt(i) === 'T' || s1.charAt(i) === 'T' && s2.charAt(i) === 'C')
                transitions++
            else
                transversions++
        System.out.println(transitions.toDouble() / transversions)
        stdin.close()
    }
}
