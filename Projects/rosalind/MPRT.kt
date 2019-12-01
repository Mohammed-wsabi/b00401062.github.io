package rosalind

import java.io.IOException
import java.net.URL
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors

object MPRT {
    private val pattern = Pattern.compile("(?=N[^P][ST][^P])")
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        while (stdin.hasNext()) {
            val id = stdin.next()
            val seq = StringBuilder()
            val url = URL("http://www.uniprot.org/uniprot/$id.fasta")
            val urlin = Scanner(url.openStream())
            urlin.nextLine()
            while (urlin.hasNext()) seq.append(urlin.next())
            val matcher = pattern.matcher(seq)
            val list: MutableList<Int> = ArrayList()
            while (matcher.find()) list.add(matcher.start() + 1)
            if (list.size != 0) {
                stdout.println(id)
                stdout.println(list.stream().map { obj: Int -> obj.toString() }.collect(Collectors.joining(" ")))
            }
            urlin.close()
        }
        stdin.close()
    }
}