import java.io.*
import java.util.*
import java.util.stream.Collectors

object CONS {
    private fun put(table: Array<IntArray>, seq: String?) {
        for (i in table.indices)
            table[i]["ACGT".indexOf(seq!!.charAt(i))]++
    }

    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val stdin = Scanner(System.`in`)
        var table: Array<IntArray>? = null
        val name: String? = null
        var seq: String? = null
        while (true) {
            if (!stdin.hasNext()) {
                put(table!!, seq)
                break
            }
            val line = stdin.next()
            if (line.charAt(0) === '>') {
                if (seq != null) {
                    if (table == null)
                        table = Array(seq.length()) { IntArray(4) }
                    put(table, seq)
                }
                seq = ""
            } else
                seq += line
        }
        for (i in table!!.indices) {
            val max = Arrays.stream(table[i]).max().getAsInt()
            System.out.print("ACGT".charAt(Arrays.stream(table[i]).boxed().collect(Collectors.toList()).indexOf(max)))
        }
        System.out.println()
        for (j in 0..3) {
            System.out.print(String.format("%c:", "ACGT".charAt(j)))
            for (i in table.indices)
                System.out.print(String.format(" %d", table[i][j]))
            System.out.println()
        }
        stdin.close()
    }
}
