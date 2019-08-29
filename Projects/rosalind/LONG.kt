import java.io.*
import java.util.*

object LONG {
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val reads = ArrayList()
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                reads.add(seq)
                break
            }
            val line = stdin.next()
            if (line.charAt(0) === '>') {
                if (seq != null)
                    reads.add(seq)
                seq = ""
            } else
                seq += line
        }
        reads.sort(Collections.reverseOrder(Comparator.comparingInt(???{ String.length() })))
        var genome = reads.remove(0)
        while (reads.size() > 0) {
            for (i in 0 until reads.size()) {
                val read = reads.get(i)
                var matched = false
                run {
                    var j = 0
                    val m: Int
                    while (j < read.length() / 2) {
                        if (read.substring(j, read.length()).equals(genome.substring(0, read.length() - j))) {
                            genome = read.substring(0, j) + genome
                            matched = true
                            break
                        }
                        j++
                    }
                }
                if (matched) {
                    reads.remove(i)
                    break
                }
                var j = read.length() / 2
                val m: Int
                while (j <= read.length()) {
                    if (read.substring(0, j).equals(genome.substring(genome.length() - j))) {
                        genome = genome + read.substring(j)
                        matched = true
                        break
                    }
                    j++
                }
                if (matched) {
                    reads.remove(i)
                    break
                }
            }
        }
        System.out.println(genome)
        stdin.close()
    }
}
