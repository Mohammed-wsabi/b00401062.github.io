import java.io.*
import java.util.*
import java.util.regex.*
import java.util.stream.*
import java.net.*

object MPRT {
    private val pattern = Pattern.compile("(?=N[^P][ST][^P])")
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        val stdout = System.out
        while (stdin.hasNext()) {
            val id = stdin.next()
            val line: String? = null
            val seq = StringBuilder()
            val url = URL("http://www.uniprot.org/uniprot/$id.fasta")
            val urlin = Scanner(url.openStream())
            urlin.nextLine()
            while (urlin.hasNext())
                seq.append(urlin.next())
            val matcher = pattern.matcher(seq)
            val list = ArrayList()
            while (matcher.find())
                list.add(matcher.start() + 1)
            if (list.size() !== 0) {
                stdout.println(id)
                stdout.println(list.stream().map(???({ String.valueOf() })).collect(Collectors.joining(" ")))
            }
            urlin.close()
        }
        stdin.close()
    }
}
